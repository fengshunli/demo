package com.fsl.gabriel.controller;

import com.fsl.gabriel.entity.User;
import com.fsl.gabriel.repository.UserRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository repository;

	@PostMapping("/")
	public void addUser(User user, HttpServletResponse response) throws Exception {
		//导入到excel
		//1.构造数据
		List<User> list = new ArrayList<User>();
		list.add(user);
		//2.创建工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();
		//3.构造sheet
		String[] titles = {"id", "username", "nickname","age"};
		Sheet sheet = workbook.createSheet();
		Row row = sheet.createRow(0);
		AtomicInteger headersAi = new AtomicInteger();
		for (String title : titles) {
			Cell cell = row.createCell(headersAi.getAndIncrement());
			cell.setCellValue(title);
		}
		AtomicInteger datasAi = new AtomicInteger(1);
		Cell cell = null;
		for (User user1 : list) {
			Row dataRow = sheet.createRow(datasAi.getAndIncrement());
			//编号
			cell = dataRow.createCell(0);
			cell.setCellValue(user1.getId());
			//姓名
			cell = dataRow.createCell(1);
			cell.setCellValue(user1.getUsername());
			cell = dataRow.createCell(2);
			cell.setCellValue(user1.getNickname());
			cell = dataRow.createCell(3);
			cell.setCellValue(user1.getAge());
		}
		String fileName = URLEncoder.encode("人员信息.xlsx", "UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("content-disposition", "attachment;filename=" + new
				String(fileName.getBytes("ISO8859-1")));
		response.setHeader("filename", fileName);
		workbook.write(response.getOutputStream());
		repository.insert(user);

	}

	@DeleteMapping("/")
	public String deleteUserById(User user){
		if(repository.deleteById(user) >0 ){
			return "删除成功";
		}else{
			return "删除失败";
		}
	}

	@PutMapping("/")
	public String updateUserById(User user){
		//repository.updateById(user)
		if(repository.deleteById(user) > 0){
			return "修改成功";
		}else{
			return "修改失败";
		}
	}

	@GetMapping("/{id}")
	public User selectUserById(@PathVariable("id")int id){
		//repository.unique(id);
		return repository.single(id);
	}

	@GetMapping("/")
	public List<User> getUsers(){
		//repository.all(1,2);
		//repository.allCount();
		return repository.all(1,2);
	}

	@GetMapping("/test")
	public List<User> getUsersByTest(){
		return repository.selectByTest();
	}
}