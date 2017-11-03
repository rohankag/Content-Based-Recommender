<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adaptive Web</title>
</head>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<body>
	<div class="content">
		<h1 style="text-align: center;">Adaptive Web- Assignment 3</h1>

		<form action="Controller1" method="post">
			<input type="submit" value="Crawl" style="color: red">
		</form>
		<div style="text-align: left; color: red;">click to crawl and
			index!.. please do only once!! This will crawl content from wikibooks
			and Oracle Java tutorials.</div>
		<br> <br> <br>
		<form action="Controller" method="post">
			Custom Search: <input type="text" name="searchquery"> <input
				type="submit" value="submit">
		</form>
		<br> <br>
		<div style="text-align: center; color: red;">Click submit and
			scroll down for recommendations</div>
		<br>
		<br>
		<br>

		<form action="Controller" method="post">
			Query 1:
			<textarea name="searchquery" cols="100" rows="4" readonly="readonly">I was presented with this question in an end of module open book exam today and found myself lost. i was reading Head first Javaand both definitions seemed to be exactly the same. i was just wondering what the MAIN difference was for my own piece of mind. i know there are a number of similar questions to this but, none i have seen which provide a definitive answer.Thanks, Darren</textarea>
			<input type="submit" value="submit" /><br>
		</form>
		<br>
		<form action="Controller" method="post">
			Query 2:
			<textarea name="searchquery" cols="100" rows="4" readonly="readonly">Inheritance is when a 'class' derives from an existing 'class'.So if you have a Person class, then you have a Student class that extends Person, Student inherits all the things that Person has.There are some details around the access modifiers you put on the fields/methods in Person, but that's the basic idea.For example, if you have a private field on Person, Student won't see it because its private, and private fields are not visible to subclasses.Polymorphism deals with how the program decides which methods it should use, depending on what type of thing it has.If you have a Person, which has a read method, and you have a Student which extends Person, which has its own implementation of read, which method gets called is determined for you by the runtime, depending if you have a Person or a Student.It gets a bit tricky, but if you do something likePerson p = new Student();p.read();the read method on Student gets called.Thats the polymorphism in action.You can do that assignment because a Student is a Person, but the runtime is smart enough to know that the actual type of p is Student.Note that details differ among languages.You can do inheritance in javascript for example, but its completely different than the way it works in Java.</textarea>
			<input type="submit" value="submit" /><br>
		</form>
		<br>
		<form action="Controller" method="post">
			Query 3:
			<textarea name="searchquery" cols="100" rows="4" readonly="readonly">Polymorphism: The ability to treat objects of different types in a similar manner.Example: Giraffe and Crocodile are both Animals, and animals can Move.If you have an instance of an Animal then you can call Move without knowing or caring what type of animal it is.Inheritance: This is one way of achieving both Polymorphism and code reuse at the same time.Other forms of polymorphism:There are other way of achieving polymorphism, such as interfaces, which provide only polymorphism but no code reuse (sometimes the code is quite different, such as Move for a Snake would be quite different from Move for a Dog, in which case an Interface would be the better polymorphic choice in this case.In other dynamic languages polymorphism can be achieved with Duck Typing, which is the classes don't even need to share the same base class or interface, they just need a method with the same name.Or even more dynamic like Javascript, you don't even need classes at all, just an object with the same method name can be used polymorphically.</textarea>
			<input type="submit" value="submit" /><br>
		</form>
		<br>
		<form action="Controller" method="post">
			Query 4:
			<textarea name="searchquery" cols="100" rows="4" readonly="readonly">I found out that the above piece of code is perfectly legal in Java. I have the following questions. ThanksAdded one more question regarding Abstract method classes.public class TestClass{public static void main(String[] args) {TestClass t = new TestClass();}private static void testMethod(){abstract class TestMethod{int a;int b;int c;abstract void implementMe();}class DummyClass extends TestMethod{void implementMe(){}}DummyClass dummy = new DummyClass();}}</textarea>
			<input type="submit" value="submit" /><br>
		</form>
		<br>
		<form action="Controller" method="post">
			Query 5:
			<textarea name="searchquery" cols="100" rows="4" readonly="readonly">In java it's a bit difficult to implement a deep object copy function. What steps you take to ensure the original object and the cloned one share no reference? </textarea>
			<input type="submit" value="submit" /><br>
		</form>
		<br>
		<form action="Controller" method="post">
			Query 6:
			<textarea name="searchquery" cols="100" rows="4" readonly="readonly">You can make a deep copy serialization without creating some files. Copy: Restore:ByteArrayOutputStream bos = new ByteArrayOutputStream();ObjectOutputStream oos = new ObjectOutputStream(bos);oos.writeObject(object);oos.flush();oos.close();bos.close();byte[] byteData = bos.toByteArray();; ByteArrayInputStream bais = new ByteArrayInputStream(byteData);(Object) object = (Object) new ObjectInputStream(bais).readObject();</textarea>
			<input type="submit" value="submit" /><br>
		</form>
		<br>
		<form action="Controller" method="post">
			Query 7:
			<textarea name="searchquery" cols="100" rows="4" readonly="readonly">Java has the ability to create classes at runtime. These classes are known as Synthetic Classes or Dynamic Proxies. See for more information. Other open-source libraries, such as and also allow you to generate synthetic classes, and are more powerful than the libraries provided with the JRE. Synthetic classes are used by AOP (Aspect Oriented Programming) libraries such as Spring AOP and AspectJ, as well as ORM libraries such as Hibernate. </textarea>
			<input type="submit" value="submit" /><br>
		</form>
		<br>
		<form action="Controller" method="post">
			Query 8:
			<textarea name="searchquery" cols="100" rows="4" readonly="readonly">A safe way is to serialize the object, then deserialize.This ensures everything is a brand new reference.about how to do this efficiently. Caveats: It's possible for classes to override serialization such that new instances are created, e.g. for singletons.Also this of course doesn't work if your classes aren't Serializable.</textarea>
			<input type="submit" value="submit" /><br>
		</form>
		<br>
		<form action="Controller" method="post">
			Query 9:
			<textarea name="searchquery" cols="100" rows="4" readonly="readonly">comment this code       /*if (savedinstancestate == null) {     getsupportfragmentmanager().begintransaction()             .add(r.id.container  new placeholderfragment())             .commit(); }*/  </textarea>
			<input type="submit" value="submit" /><br>
		</form>
		<br>
		<form action="Controller" method="post">
			Query 10:
			<textarea name="searchquery" cols="100" rows="4" readonly="readonly"> in a class i can have as many constructors as i want with different argument types. i made all the constructors as private it didn't give any error because my implicit default constructor was public but when i declared my implicit default constructor as private then its showing an error while extending the class.  why?       this works fine         this can not be inherited       this(\"unknown\"  20);         this.sal=2000;     }      void show(){         system.out.println(\"name\"+name);         system.out.println(\"age: \"+age);     } }  public class demo4  {     private string name;     private int age;     private double sal;      private demo4(string name  int age) {         this.name=name;         this.age=age;     }      demo4(string name) {         this.name=name;     }      private demo4() {         this(\"unknown\"  20);         this.sal=2000;     }      void show() {         system.out.println(\"name\"+name);         system.out.println(\"age: \"+age);     } }  </textarea>
			<input type="submit" value="submit" /><br>
		</form>
		<br> <br> <br> <br></div>
		<h2 style="text-align: center; color: red">
			How Contents are indexed on web pages
			</h2>
			<ul>
				<li>Main Text Content is extracted from the web pages and
					accordingly different documents are created for each topic</li>
				<li>Search query is skimmed to smaller keywords and each
					keyword is searched through crawled documents in the Library
					directory</li>
				<li>Apache Lucene Stores the terms in index file where each term is associated with documents that contain it.</li>	
				<li>Terms are generated using an analyzer which each stems each word to its root form,we have used Porter Stemming algorithm</li>
				<li>When a query is issued it is processed through the same analyzer that is used to build the index and accordingly matching terms are looked upon.</li>
				<li>List of documents matching the query are returned</li>
			</ul>
			<br>

			<%
				String query = (String) request.getAttribute("query_str");
			%>
			<div>
				<%
					List<String> output = (List<String>) request.getAttribute("url");
					if (output != null) {
				%>
				<div>
					<h4>Requested Query:</h4><%=query%>
				</div>
				<br>
				<div>
					<h4>
						<center>TOP 10 Recommended Posts</center>
					</h4>
				</div>
				<%
					Iterator<String> it = output.iterator();
						int x = 0;
						while (it.hasNext() && x< 10) {
				%>
				<div class="title">
					<center>
						<b style="background-color: yellow; font-size: 14">RECOMMENDATION
							<%=++x%></b> <br>
					</center>
				</div>
				<div class="reslt">
					<p class="text" style="background-color: gray;"><%=it.next()%></p>
				</div>
				<%
					}
					}
				%>
			</div>