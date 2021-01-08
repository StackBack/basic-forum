# basic-forum
This project based on Spring Framework, H2 database (in-memory) and JWT system.

-When you start this project, you need go to "/api/auth/signup" url for registation. If you registration successfully you receive message that account created.
-Than, you go to "/api/auth/signin" for login at the system. If you successfully log in, you receive a JSON object with the fields "Token", "Valid to", "Username".
-If user want to go other url, need to copy value "Token" and past to "Bearer Token" Authorization.

URL:

-(POST)"/api/auth/signup" - registration user;
-(POST)"/api/auth/signin" - log in user;
-(GET)"/users/by-color?color=..." - returns a JSON object with users whose article color is your value;
-(GET)"/users/by-age?age=..." - returns a JSON object with users who are older than or equal to your value;
-(GET)"/users/count" - returns a JOHN object with users who have 3 or more articles;
-(POST)"/users/art" - you send a JSON object with the fields "Text" and "Color". If created successfully you will receive the message "Article successfully created"
