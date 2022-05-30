# congwei_web
个人博客
1.前端代码文件
a.login.html                      （待完善）  (a~d & g bootstrap有待完善）
b.register.html                   （待完善）
c.blog.html                       （待完善） 
d.edit.html                       （待完善）
e.lastPage.html                   （待修改） （暂未实现翻页功能）
f.Failed.html                     （待完善） （进入界面后需手动点击网页自带的返回上一页按钮）
g.update.html                     （待完善）
h.web.css                         （完  成） 

后端代码文件
a.BlogUserController.java         （待完善）（统一账户可重复创建）
b.BlogUserController.java         （待完善）（没有账户也可以无账户进入博客界面进行新增、更新、删除等操作）
c.BlogUserInfo.java               （完  成） 
d.BlogsInfo.java                  （完  成） 
e.BlogUserInfoRepository.java     （完  成）
f.BlogsRepository.java            （完  成）
 
单元测试：
a.BlogUserInfoControllerTest.java 
(login/register的get/post方法的成功失败测试通过，BlogUserInfoControllerTest的覆盖率97.2%，
Controller中没有标红、黄的代码行，60行有一个蓝点，提示内容如下：
Multiple markers at this line
	- Line breakpoint:BlogUserController [line: 60] - login(String, String, 
	 ModelAndView)，
  没找到其他未覆盖的地方)
