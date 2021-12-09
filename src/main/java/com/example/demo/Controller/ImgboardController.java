package com.example.demo.Controller;

import com.example.demo.dto.IMGBoardDTO;
import com.example.demo.entity.IMGBoard;
import com.example.demo.service.IMGBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Controller
@Log4j2
@RequiredArgsConstructor
public class ImgboardController {

    //업로드 패스 선언 (윈도우 운영체제는 역슬래쉬 지정)
    private String uploadPath="c:\\upload";

    @Autowired
    private final IMGBoardService service;

    @GetMapping("/Imgboard/list.do")
    public String ImgboardlistPage(Model model) {
        List<IMGBoard> list = service.getTotalIMGBoard();
        //model로 key값 value값
        model.addAttribute("list",list);
        return "/Imgboard/list.html";
    }

    @GetMapping("/Imgboard/post.do")
    public String ImgboardpostPage(Model model) {

        return "/Imgboard/post.html";
    }

    @PostMapping("/Imgupload.do")
    public String ImguploadProc(IMGBoardDTO dto, HttpServletRequest request){
        //request에 body정보가 담겨서 오는지 확인(꺼내오기)
        try {
                //request에서 part 꺼내오는 작업(모두 꺼내는 작업)
//            for (Part part:request.getParts()){
//                System.out.println("폼태그명 : " + part.getName());
//                System.out.println("파일Size : " + part.getSize());

                //request에서 part 꺼내오는 작업(원하는 부분만 꺼내는 작업)
                Part part = request.getPart("fileform");
                System.out.println("이름 : " + part.getName());
                System.out.println("파일사이즈 : " + part.getSize());
                //header이름을 가져온다.(헤더와 헤더값은 key, value로 구성되어 있음)
                for(String name: part.getHeaderNames()){
                    System.out.println("HeaderName : " + name);
                    System.out.println("HeaderValue : " + part.getHeader(name));
                }

                //파일 이름 추출(확인)
                //(contentDisposition의 value들을 contentDisp에 저장한다.)
                String contentDisp = part.getHeader("content-disposition");
                //contentDisp에 저장된 배열을 tokens에 대입
                String[] tokens = contentDisp.split(";"); //[form-data, name=Filename, filename="dog1.jpg"]
                //tokens에 저장된 2번째 값 fileName 추출(결과 : filename="dog1.jpg")
                String fileName =tokens[2];
                //추출된 값에서 11번째에서 길이만큼 -1 해준다. (-1: 마지막 겹따옴표 제거하기 위함) / 추출결과 : dog1.jpg
                fileName = fileName.substring(11,fileName.length()-1);
                System.out.println("추출된 파일명 확인 : " + fileName);

                //날짜 폴더 생성 (makeFolder : 날짜별로 끊어내기 위한 메서드 2021, 12, 08 값 folderpath에 저장)
                String folderpath = makeFolder();

                //UUID 값 생성(이름 중복 방지용)
                String uuid = UUID.randomUUID().toString();

                //원본파일 절대 경로 저장
                String saveName=uploadPath+File.separator+folderpath+File.separator+uuid+"_"+fileName;

                //원본파일 저장 (part를 꺼내와서 write 사용해서 넣어줌) > 저장 후 list에 표시 되도록 해야 함
                part.write(saveName);

                //썸네일 경로 지정
                //(경로인자를 넣어주기 위해서 패스 경로 사용해줘야함(String의 경로를 Path클래스로)
                // 파일 절대경로값 앞에 S_넣어서 썸네일 이미지란 것 표시해서 이미지 파일명으로 구분 가능)
                String ThumnailsaveName=uploadPath+File.separator+folderpath+File.separator+"S_"+uuid+"_"+fileName;

                //원본 파일경로(Path 객체로 변환 후 전달해 줘야함) 사용이유 : 원본 이미지에서 썸네일 이미지 생성하기 때문
                Path savePath= Paths.get(saveName);

                //썸네일 파일경로
                File ThumnailFile = new File(ThumnailsaveName);

                //썸네일파일 저장하기(해당 경로에 "원본파일경로","썸네일파일경로","너비","높이" / 파일명은 객체형으로 바꿔서 전달해야함)
                //파일이 깨지는 것을 방지하기 위해 원본의 가로/세로 구해서 비율에 맞게 축소 시키는 작업도 필요.
                Thumbnailator.createThumbnail(savePath.toFile(),ThumnailFile,100,100);

                //DB에 저장처리
                //DTO에
                System.out.println("DTO정보 : " + dto.toString());
                //DB에 전달할 내용 dto에 SET(db로 보내기 전 값 담는 작업)
                dto.setFilename(uuid+"_"+fileName);
                dto.setS_filename("S_"+uuid+"_"+fileName);
                //replace를 통해 \(역슬래쉬)를 /(슬래쉬)로 바꿔서 db에 값 삽입한다. (list에서 이미지 /(슬래쉬)기준으로 가져오기 위함도 있음)
                dto.setFolderpath(folderpath.replace(File.separator,"/"));

                //서비스 함수를 이용해 DB에 DTO 내용 Insert
                service.PostImg(dto);

                //페이지 연결(이미지 리스트 페이지로 이동)

            }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/Imgboard/list.do";
    }

    private String makeFolder(){
        //연, 월, 일 정보가 가지고 와줌 > 원하는 형태로 format 지정
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        //슬래시 기호를 File.separator로 변형 (운영체제에 따라 알아서 widows:\  mac:/ 지정해줌)
        String folderPath=str.replace("/", File.separator);
        //폴더 잘 분리 됐는지 콘솔창에서 확인
        System.out.println("폴더 경로 : " + folderPath);

        //폴더 생성 메서드 (기본 업로드 패스에서 folderPath 경로 생성 후 연결)
        //c:\\upload\2012\12\08
        File uploadPathFolder = new File(uploadPath,folderPath);
        //존재하지 않는다면 folder 만들겠다.
        if(uploadPathFolder.exists()==false)
            uploadPathFolder.mkdirs();

        return folderPath; // ex) 2021\12\08
    }


    //이미지를 클릭했을 때 원본 파일을 볼 수 있도록
    @GetMapping("/Imgboard/read.do")
    //dto: 게시물 번호 받기 위한 용도
    //model: 내용 출력하기 위한 용도
    //req: 션에 읽고 잇는 게시물 추가하는 용도
    public String readpage(IMGBoardDTO dto,Model model,HttpServletRequest req) {
        System.out.println("읽고있는 게시물 NUM : " + dto.getNum());

        //Service함수이용해서 num에 해당되는 게시물 가져오기 (dto로부터 전달받은 Num board에 담아주고 세션에 담아주는 작업)
        IMGBoard board = service.getIMGBoard(dto.getNum());

        //세션 객체에 생성 후 세션에 읽고 있는 게시물 추가
        HttpSession session = req.getSession();
        session.setAttribute("board",board);

        //Model 이용해서 페이지로 읽고 있는 게시물 전달
        model.addAttribute("board",board);

        return "/Imgboard/read.html";
    }


}
