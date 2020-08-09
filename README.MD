In order to run this application you need to have installed Java 11 and Maven.

Clone the repository:
git clone git@github.com:florinani/ing.git

Go to the cloned repository and build the project with the command:
mvn clean package

Run the project:
java -jar target/ing-0.0.1-SNAPSHOT.jar

Go to your brownser and open:
localhost:8080

You can login with the user: vasilepop  pass: password123

To add a new account you can press ADD ACCOUNT insert the name of the account, the currency and pres submit:

Be aware that some validation are putted in place:

1. You can create an account only if the logged in user doesn't already have one created.
2. You can create an account only from Monday to Friday.
3. You can create an account only between 9:00 AM and 17:00 PM



