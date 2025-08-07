package com.jobboard.JobBoardApplication.java;

import com.jobboard.dao.*;
import com.jobboard.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.util.Scanner;
import java.util.List;

public class JobBoardCLI {

    private JdbcTemplate jdbcTemplate;
    private UserDao userDao;
    private CategoryDao categoryDao;
    private JobDao jobDao;
    private SkillDao skillDao;
    private ApplicationDao applicationDao;


    public static void main(String[] args) {

        JobBoardCLI app = new JobBoardCLI();
        app.run();
    }


    public JobBoardCLI() {

        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/FreeLancer");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
        dataSource.setAutoCommit(true);

        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.categoryDao = new JdbcCategoryDao(jdbcTemplate);
        this.jobDao = new JdbcJobDao(jdbcTemplate);
        this.skillDao = new JdbcSkillDao(jdbcTemplate);
        this.applicationDao = new JdbcApplicationDao(jdbcTemplate);
        this.userDao = new JdbcUserDao(jdbcTemplate);

    }

    public void run() {
        System.out.println("Welcome to the Freelancer Job Board!");

        //Demo Username: cali (client) password: cali
        //Demo Username: ruslerm (FreeLancer) password marissa

        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        boolean isLoggedIn = false;
        User currentUser = null;

        while (running) {
            while(!isLoggedIn) {

                System.out.println("1: Login");

                System.out.println("2: Create Account");

                System.out.println("3: Exit App");

                String choice = scanner.nextLine();

                if (choice.equals("1")) {

                    System.out.println("Enter Username:");

                    String username = scanner.nextLine();

                    User user = getUserByUsername(username);

                    System.out.println("Enter Password:");

                    String password = scanner.nextLine().trim();

                    if (user == null) {
                        System.out.println("Wrong UserName");

                    } else if (password.equals(user.getHashedPassword())) {


                        System.out.println("Logged In");

                        isLoggedIn = true;

                        currentUser = user;

                    } else {
                        System.out.println("Password Incorrect");
                    }


                } else if (choice.equals("2")) {

                    System.out.println("Create Account");

                    System.out.println("Enter Email:");

                    String email = scanner.nextLine();

                    System.out.println("Role (Client or FreeLancer):");

                    String role = scanner.nextLine();

                    role = role.toLowerCase();

                    System.out.println("Create Username:");

                    String userName = scanner.nextLine().trim();

                    System.out.println("Create Password:");

                    String password1 = scanner.nextLine().trim();

                    password1 = password1.trim();

                    System.out.println("Confirm Password:");

                    String password2 = scanner.nextLine().trim();

                    password2 = password2.trim();


                    if (!password1.equals(password2)) {

                        System.out.println("Passwords don't match!!!!");

                    } else if (password1.equals(password2)) {

                        createUser(email, role, userName, password1);

                        System.out.println("Account Created!!!");

                    }

                }else if(choice.equals("3")){
                    System.out.println("Until Next Time");

                    scanner.close();

                    return;
                }


                while (isLoggedIn) {
                    if (currentUser.getRole().equalsIgnoreCase("freelancer")) {
                        freelancerMenu(scanner, currentUser);
                        isLoggedIn = false;
                        running = false;
                    } else if (currentUser.getRole().equalsIgnoreCase("client")) {
                        clientMenu(scanner, currentUser);
                        isLoggedIn = false;
                        running = false;
                    }

                }
           }

        }
    }




    private void viewAllUsers() {
        List<User> users = userDao.getUsers();
        System.out.println("\nUsers:");
        for (User user : users) {
            System.out.println(user.getId() + ": " + user.getUsername() + " (" + user.getRole() + ")");
        }
    }

    private User getUserByUsername(String userName) {
        User user = userDao.getUserByUsername(userName);
        return user;
    }

    private void createUser(String email, String role, String userName, String password) {
       User newUser = new User();

        newUser.setUsername(userName);
        newUser.setEmail(email);
        newUser.setHashedPassword(password);
        newUser.setRole(role);

        User user = userDao.createUser(newUser);

    }
    private void viewAllCategories() {
        List<Category> categories = categoryDao.getAllCategories();
        System.out.println("\nCategories:");
        for (Category c : categories) {
            System.out.println(c.getCategory_id() + ": " + c.getName());
        }
    }


    private void viewAllJobs() {
        List<Job> jobs = jobDao.getAllJobs();
        System.out.println("\nJobs:");
        for (Job j : jobs) {
            System.out.println(j.getJob_id() + ": " + j.getTitle() + " - " + j.getDescription());
        }
    }
    private void viewAllSkills() {
        List<Skill> skills = skillDao.getAllSkills();
        System.out.println("\nSkills:");
        for (Skill s : skills) {
            System.out.println(s.getSkillId() + ": " + s.getName());
        }
    }
    private void viewAllApplications() {
        List<Application> apps = applicationDao.getAllApplications();
        System.out.println("\nApplications:");
        for (Application a : apps) {
            System.out.println("Application ID: " + a.getApplication_id()
                    + " | Freelancer ID: " + a.getFreelancer_id()
                    + " | Job ID: " + a.getJob_id()
                    + " | Status: " + a.getStatus());
        }
    }
    private void freelancerMenu(Scanner scanner, User currentUser) {
        boolean browsing = true;
        while (browsing) {
            System.out.println("\nFreelancer Menu:");
            System.out.println("1: View Available Jobs");
            System.out.println("2: Apply to a Job");
            System.out.println("3: View My Applications");
            System.out.println("4: View Available Skills");
            System.out.println("5: Create Your Application");
            System.out.println("6: Logout");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    viewAllJobs();
                    break;
                case "2":
                    applyToJob(scanner, currentUser);
                    break;
                case "3":
                    viewFreelancerApplications(currentUser.getId());
                    break;
                case "4":
                    viewAllSkills();
                    break;
                case "5":
                    createFreelancerApplication(scanner, currentUser);
                    break;
                case "6":
                    browsing = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void clientMenu(Scanner scanner, User client) {
        boolean managing = true;
        while (managing) {
            System.out.println("\nClient Menu:");
            System.out.println("1: Post a Job");
            System.out.println("2: View Your Jobs");
            System.out.println("3: Logout");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    postJob(scanner, client);
                    break;
                case "2":
                    viewJobsByClient(client.getId());
                    break;
                case "3":
                    managing = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void postJob(Scanner scanner, User client) {
        System.out.println("Enter job title:");
        String title = scanner.nextLine();

        System.out.println("Enter job description:");
        String description = scanner.nextLine();



        List<Category> categories = categoryDao.getAllCategories();
        System.out.println("Available Categories:");

        for (Category c : categories) {
            System.out.println(c.getCategory_id() + ": " + c.getName());
        }

        System.out.println("Enter the number of the category:");
        int categoryId = Integer.parseInt(scanner.nextLine());

        Job job = new Job();
        job.setTitle(title);
        job.setDescription(description);
        job.setPosted_by(client.getId());
        job.setCategory_id(categoryId);

        jobDao.createJob(job);

        System.out.println("Job posted successfully with ID: " + job.getJob_id());
    }

    private void viewJobsByClient(int clientId) {
        List<Job> jobs = jobDao.getJobsByClientId(clientId);
        System.out.println("\nYour Posted Jobs:");
        for (Job job : jobs) {
            System.out.println(job.getJob_id() + ": " + job.getTitle() + " - " + job.getDescription());
        }
    }

    private void applyToJob(Scanner scanner, User freelancer) {
        viewAllJobs();

        System.out.println("Enter the Job ID you want to apply for:");
        int jobId = Integer.parseInt(scanner.nextLine());

        Application app = new Application();
        app.setFreelancer_id(freelancer.getId());
        app.setJob_id(jobId);
        app.setStatus("pending");

        applicationDao.createApplication(app);

        System.out.println("Application submitted!");
    }
    private void viewFreelancerApplications(int freelancerId) {
        List<Application> apps = applicationDao.getApplicationsByFreelancerId(freelancerId);

        if (apps.isEmpty()) {
            System.out.println("You have no applications.");
            return;
        }

        System.out.println("Your Applications:");
        for (Application a : apps) {
            System.out.println("Job ID: " + a.getJob_id() + ", Status: " + a.getStatus());
        }
    }
    private void createFreelancerApplication(Scanner scanner, User freelancer) {
        Application app = new Application();

        System.out.println("Enter your full name:");
        String name = scanner.nextLine();

        System.out.println("Enter your top skill:");
        String skill = scanner.nextLine();

        System.out.println("Enter a short recommendation or bio:");
        String recommendation = scanner.nextLine();

        System.out.println("Available jobs:");
        viewAllJobs();

        System.out.println("Enter Job ID you'd like to apply to:");
        int jobId = Integer.parseInt(scanner.nextLine());


        app.setFreelancer_id(freelancer.getId());
        app.setJob_id(jobId);
        app.setStatus("Pending");


        applicationDao.createApplication(app);

        System.out.println("Application submitted!");


        Skill newSkill = new Skill();
        newSkill.setName(skill);
        newSkill.setUserId(freelancer.getId());

        skillDao.createSkill(newSkill);
        System.out.println("Skill saved!");
    }








}


