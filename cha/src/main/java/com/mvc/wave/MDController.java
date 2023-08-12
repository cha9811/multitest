package com.mvc.wave;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mvc.wave.DAO.MDCommentDAO;
import com.mvc.wave.DAO.MDImgDAO;
import com.mvc.wave.DAO.MDPostDAO;
import com.mvc.wave.DTO.MDCommentDTO;
import com.mvc.wave.DTO.MDPostDTO;

//해당 패키지 controller로 바꾸면 에러가 나타납니다.. 일단 wave로 진행중

@Controller

public class MDController {

	@Autowired
	MDPostDAO MDpostDAO;

	@Autowired
	MDImgDAO MDimgDAO;

	@Autowired
	MDCommentDAO MDcommentDAO;

	// 전체 게시글 보기(posts+img)
	@RequestMapping(value = "MDAll", method = RequestMethod.GET)
	public void MDAll(Model model1, Model model2) {
		List<MDPostDTO> mdpostall = MDpostDAO.MDAll();
		model1.addAttribute("mdpostall", mdpostall);

	}

	// 게시글 상세보기 -> 이미지,댓글,게시글 전부 가져옴 (content+img+posts)
	@RequestMapping(value = "/MDDetail", method = RequestMethod.GET)
	public String MDDetail(@RequestParam("id") int id, Model model1, Model model2, Model model3) {
		// id를 이용하여 상세 정보를 조회하는 로직 구현
		MDPostDTO postdto = MDpostDAO.getMDPostById(id);

//		List<MDImgDTO> imgdto = MDimgDAO.getMDImgById(id);
		List<MDCommentDTO> commentdto = MDcommentDAO.getMDCommentById(id);

		// 조회한 정보를 상세페이지로 전달(게시글 텍스트)
		model1.addAttribute("postdto", postdto);
		// 조회한 정보를 상세페이지로 전달(게시글 이미지)
//		model2.addAttribute("imgdto", imgdto);
		// 조회한 정보를 상세페이지로 전달(게시글 댓글)
		model3.addAttribute("commentdto", commentdto);
		return "MDDetail"; // 상세페이지 뷰 이름
	}

	// 게시글 검색하기(img+posts)

	@RequestMapping("MDPostsearch")
	public String MDPostSearch() {

		return "redirect:/MDAll";
	}

	// 게시글 만들기 (img+posts)
	
	@RequestMapping(value = "/MDPostMake", method = RequestMethod.POST)
	public String MDPostMake(@ModelAttribute MDPostDTO mdPostDTO,Model model) {
//			@RequestParam(value = "MD_post_Thumbnail", required = false) MultipartFile file, HttpServletRequest request,
//			HttpServletRequest request, Model model) {
//		if (file.isEmpty() || file.getOriginalFilename().isEmpty()) {
//			System.out.println("파일이 없거나 파일 이름이 없습니다.");
//			return "redirect:/MDAll"; // 여기에 적절한 리다이렉트나 에러 처리를 추가하세요.
//		}
//		System.out.println("File name: " + file.getOriginalFilename()); // 파일 이름 로깅
//
//		// 파일 업로드 처리
//		if (!file.isEmpty()) {
//			String savedName = file.getOriginalFilename();
//			String fileName = file.getOriginalFilename();
//			// 파일을 저장할 경로 설정
//			String uploadPath = request.getSession().getServletContext().getRealPath("resources/upload"); // 실제 경로로 수정
//			File target = new File(uploadPath + "/" + savedName);
//
//			try {
//				file.transferTo(target); // 실제 파일 저장
//				// 파일 이름을 MDPostDTO에 저장
////				mdPostDTO.setMD_post_Thumbnail(savedName);
//			} catch (IOException e) {
//				e.printStackTrace();
//				// 파일 저장 중 오류 발생 시 예외 처리
//			}
//		}
		System.out.println("hello4");
		MDpostDAO.MDPostinsert(mdPostDTO);
		return "redirect:/MDAll";
	}

	// 업데이트 페이지이동
	@RequestMapping(value = "/MDPostUpdatePage", method = RequestMethod.GET)
	public String mdPostUpdatePage(@RequestParam("id") int id, Model model) {
		MDPostDTO mdPostDTO = MDpostDAO.getMDPostById(id);
		model.addAttribute("mdPostDTO", mdPostDTO);
		return "MDPostUpdate";
	}

	// 게시글 업데이트
	@RequestMapping(value = "/MDPostUpdate", method = RequestMethod.POST)
	public String mdPostUpdate(MDPostDTO mdPostDTO) {
		MDpostDAO.MDPostUpdate(mdPostDTO);
		return "redirect:/MDDetail?id=" + mdPostDTO.getMD_id();
	}

	// 게시글 삭제하기
	@RequestMapping("MDPostDelete")
	public String MDPostDelete(MDPostDTO mdPostDTO, RedirectAttributes redirectAttributes) {
		System.out.println(mdPostDTO);
		redirectAttributes.addFlashAttribute("message", "게시글이 삭제되었습니다.");
		System.out.println("message실행?");
		MDpostDAO.MDPostDelete(mdPostDTO);
		return "redirect:/MDAll";
	}
	//////////// 댓글 /////////////

	// 댓글 달기
	@RequestMapping("MDCommentMake")
	public String MDCommentMake(MDCommentDTO mdcommentDTO) {
		MDcommentDAO.MDCommentinsert(mdcommentDTO);
		return "redirect:/MDDetail?id=" + mdcommentDTO.getMD_id();
	}

	// 댓글 삭제하기
	@RequestMapping("MDCommentDelete")
	public String MDCommentDelete(MDCommentDTO MDcommentdto, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", "댓글이 삭제되었습니다.");
		int number = MDcommentdto.getMD_id();
		MDcommentDAO.MDCommentdelete(MDcommentdto);
		System.out.println(MDcommentdto.getMD_comment_id());
		return "redirect:/MDDetail?id=" + number;
	}

	// 댓글 수정하기
	//
	@RequestMapping("MDCommentUpdate")
	public String MDCommentUpdate(MDCommentDTO MDcommentdto, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", "댓글이 수정되었습니다.");
		MDcommentDAO.MDCommentupdate(MDcommentdto);
		System.out.println(MDcommentdto.getMD_id());
		return "redirect:/MDDetail?id=" + MDcommentdto.getMD_id();
	}
}
