package lyx.houtai.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lyx.houtai.model.User;
import com.lyx.houtai.service.IUserService;
import com.lyx.houtai.web.Application;
import com.lyx.houtai.web.config.DruidConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IUserService userService;
	
/*	@Autowired
	private DruidConfiguration source;*/
	
	
	@Test
	public void sss(){
		User user = userService.queryUserById(1L);
		logger.debug("===========111===============user:"+user.toString());
		//System.out.println("=============source-url:"+source.getUrl()+"=============");
	}
	
	public static void main(String[] args) {
		for (int i = 0 ; i < 10; i ++) {
			System.out.println( Math.random() );
			System.out.println( (int)( (Math.random() * 9 + 1) * 10000) );
		}
	}

}

