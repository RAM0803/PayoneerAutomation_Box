# PayoneerAutomation_Box
AutomationScriptForBox

You can use any IDE like INtelliJ or Eclipse. I have preferred IntelliJ. In My video you can see intelliJ.

_**Please do not that to run this automation script, you should have installed Java and Maven in your system and you have set your Environment Variables class path as well.**_

**Steps to run the testcase : **

1. Either you can download from githhub directly or you can clone using Github desktiop application.
2. After downloading open the project using any IDE
3. It will automatically build as maven project, incase if it was not automatically loading as maven project. 
4. Then Open POM.xml and right click and select Add as maven project. Now it will start building and download all needed dependencies.
5. Now go to File --> Project Structure --> Left hand side select modules tab --> select src/resources --> select resources option on the top menu. Save the changes
6. Now you are all done to run the script. 

src/test/java/Tests/BoxTestCase.java run the file either by right clicking or by clicking on Green arrow.

**Project framwork details :**
1. src/test/java/Core --> This directory will have al basic core files like, setting up driver for execution. Taking screenshot. 
Also it contains a class named Core which contains all helpful generic functions like getText(), setText(), click()....
2. src/test/java/Pages --> PageModel containng all xpath and locators for the Box website. 
3. src/test/java/Tests --> MainTestFile.
4. src/TestScreenshot --> Houses all saved screenshots of the testcase.
5. src/resources --> This contains all resources like properties file and File that needs to be uploaded.
