# congwei_web
个人博客
1.前端代码文件
a.login.html                                   （待完善）  (a~d & g bootstrap有待完善）
b.register.html                                （待完善）
c.blog.html                                    （待完善） 
d.edit.html                                    （待完善）
e.lastPage.html                                （待修改） （暂未实现翻页功能）
f.Failed.html                                  （待完善） （进入界面后需手动点击网页自带的返回上一页按钮）
g.update.html                                  （待完善）
h.web.css                                      （完  成） 

后端代码文件
a.BlogUserController.java                      （待完善）（统一账户可重复创建）
b.BlogController.java（原BlogsController.java）（待完善）（没有账户也可以无账户进入博客界面进行新增、更新、删除等操作）
c.BlogUserInfo.java                            （完  成） 
d.BlogInfo.java（原BlogsInfo.java）            （完  成） 
e.BlogUserInfoRepository.java                  （完  成）
f.BlogRepository.java（原BlogsRepository.java）（完  成）
 
单元测试：
a.BlogUserControllerTest.java 
（BlogUserInfoController的覆盖率100%）
b.BlogControllerTest.java 
（BlogController覆盖率100%）

集成测试：
a.BlogUserInfoTestIT.java 
（BlogUserInfoController覆盖率100%）
b.BlogsInfoTestIT.java
（BlogController覆盖率43.8%
getEditView()	   100 %	
getLastPage()  	 100 %	
getUpdateView()	 100 %	 （覆盖率100%但是代码有问题待修改）	
getBlogView()    75.9%  （未完成，controller里没给提示）
deleteBlog()	    22.7 %	（未完成，待修改）	
addBlog()        0%	    （未编写）
update()	        0%	    （未编写）




）
