package jxl_excel; 


import java.io.File; 
import java.util.ArrayList; 
import java.util.List; 

import jxl.Workbook; 
import jxl.write.Label; 
import jxl.write.WritableSheet; 
import jxl.write.WritableWorkbook; 

class User { 
private Integer id; 
private String name; 
private String sex; 
private Integer age; 

public User() { 
} 

public User(final Integer id, final String name, final String sex, final Integer age) { 
super(); 
this.id = id; 
this.name = name; 
this.sex = sex; 
this.age = age; 
} 

public Integer getId() { 
return id; 
} 
public void setId(final Integer id) { 
this.id = id; 
} 
public String getName() { 
return name; 
} 
public void setName(final String name) { 
this.name = name; 
} 
public String getSex() { 
return sex; 
} 
public void setSex(final String sex) { 
this.sex = sex; 
} 
public Integer getAge() { 
return age; 
} 
public void setAge(final Integer age) { 
this.age = age; 
} 

} 

public class TestExcel { 


public static void main(final String[] args) { 
try { 
WritableWorkbook wwb = null; 

final String fileName = "user.xls"; 
final File file=new File(fileName); 
if (!file.exists()) { 
file.createNewFile(); 
} 

wwb = Workbook.createWorkbook(file); 


final WritableSheet ws = wwb.createSheet("Test Shee 1", 0); 
final User u1=new User(1,"È«Ãô","ÄÐ",18); 
final User u2=new User(2,"ÁõöÎ","ÄÐ",14); 
final User u3=new User(3,"ÄÌÁú","Å®",15); 
final User u4=new User(4,"Áõ³¬","Å®",11); 
final List<User> list= new ArrayList<User>(); 
list.add(u1); 
list.add(u2); 
list.add(u3); 
list.add(u4); 
for (final User user : list) { 
System.out.println(user.getId()+"="+user.getName()+"="+user.getSex()+"="+user.getAge()); 
} 
final Label labelId= new Label(0, 0, "±àºÅ(id)"); 
final Label labelName= new Label(1, 0, "ÐÕÃû(name)"); 
final Label labelSex= new Label(2, 0, "ÐÔ±ð(sex)"); 
final Label labelAge= new Label(3, 0, "ÄêÁä(age)"); 

ws.addCell(labelId); 
ws.addCell(labelName); 
ws.addCell(labelSex); 
ws.addCell(labelAge); 
for (int i = 0; i < list.size(); i++) { 
final Label labelId_i= new Label(0, i+1, list.get(i).getId()+""); 
final Label labelName_i= new Label(1, i+1, list.get(i).getName()); 
final Label labelSex_i= new Label(2, i+1, list.get(i).getSex()); 
final Label labelAge_i= new Label(3, i+1, list.get(i).getAge()+""); 
ws.addCell(labelId_i); 
ws.addCell(labelName_i); 
ws.addCell(labelSex_i); 
ws.addCell(labelAge_i); 
} 
wwb.write(); 
wwb.close(); 
} catch (final Exception e) { 
e.printStackTrace(); 
} 
} 
} 