package com.example.demo.controller;

import com.example.demo.socket.MyWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;

/**
 * @Description:  消息发送类。服务端主动发送
 */
@RestController
public class SocketController {
    @Resource
    MyWebSocket myWebSocket;
    
	/**
	  * 转发消息
	 *  
	 * @param msg
	 * @return
	 */
	@GetMapping("getmsg")
	public String getMsg(String msg) {
		
		if(msg.equals("option1")) {
			myWebSocket.sendMessage("0");
		}
		if(msg.equals("option2")) {
			myWebSocket.sendMessage("1");
		}
		return msg;

	}

    @RequestMapping("many")
    public String helloManyWebSocket(){
        //向所有人发送消息
        myWebSocket.sendMessage("你好~！");

        return "发送成功";
    }

    @RequestMapping("one")
    public String helloOneWebSocket(String sessionId) throws IOException {
        //向某个人发送消息
        myWebSocket.sendMessage(sessionId,"你好~！，单个用户");

        return "发送成功";
    }


}