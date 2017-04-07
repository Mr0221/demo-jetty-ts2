package cn.lai.netstoss.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.lai.netstoss.Entity.Admin;
import cn.lai.netstoss.service.AdminService;

/**
 *	鐢ㄦ敞瑙ｇ殑鏂瑰紡寮�彂Controller
 * 1 涓嶇敤瀹炵幇Controller
 * 2 鍙互娣诲姞澶氫釜澶勭悊鏂规硶
 * 3 澶勭悊鏂规硶
 *  a.鏂规硶鍚嶄笉鍋氳姹�
 *  b.杩斿洖鍊煎彲浠ユ槸ModelAndView
 *  鍙互鏄疭tring 锛�鍙互鏄痸oid
 *  鍖哄埆濡傛灉杩斿洖鐨勬棦鏈夋暟鎹紝 涔熸湁瑙嗗浘鍚�鍙互浣跨敤ModeAndView 濡傛灉鍙湁瑙嗗浘鍚嶅彲浠ヨ繑鍥濻tring銆�
 * 4 浣跨敤鍒扮殑娉ㄨВ锛�
 * @Controller 锛�瑕佸姞鍦ㄧ被鍓嶉潰
 *@RequestMapping锛�鍙互鍔犲埌绫诲墠闈紝 涔熷彲浠ユ坊鍔犲埌澶勭悊鏂规硶鍓嶉潰锛岀敤鏉ヨ缃姹傝矾寰勪笌澶勭悊鏂规硶鐨勫搴斿叧绯�
 */

@Controller
@RequestMapping("/demo")
public class HelloController {

    @Resource(name="adminService")
    private AdminService service;

    @RequestMapping("/testAdmin.do")
    public String testAdmin(final HttpServletRequest request){
        final Admin a = service.findByCode("caocao");
        request.setAttribute("name", a.getName());
        request.setAttribute("phone", a.getTelephone());
        return "testAdmin";
    }
    public HelloController() {

    }

    @RequestMapping("/hello.do")
    public ModelAndView toHello(){
        System.out.println("HelloController::toHello()");
        return new ModelAndView("hello");
    }
}
