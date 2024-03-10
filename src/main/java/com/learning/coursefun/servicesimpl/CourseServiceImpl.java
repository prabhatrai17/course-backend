package com.learning.coursefun.servicesimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.coursefun.entities.Course;
import com.learning.coursefun.entities.CourseCategory;
import com.learning.coursefun.entities.CourseModule;
import com.learning.coursefun.entities.CourseProgressStatus;
import com.learning.coursefun.entities.FeedBack;
import com.learning.coursefun.entities.ModuleAssesmentStatus;
import com.learning.coursefun.entities.ModuleTopic;
import com.learning.coursefun.entities.User;
import com.learning.coursefun.repo.CourseCategoriesRepo;
import com.learning.coursefun.repo.CourseModuleRepo;
import com.learning.coursefun.repo.CourseRepo;
import com.learning.coursefun.repo.UserRepo;
import com.learning.coursefun.services.CourseProgressStatusService;
import com.learning.coursefun.services.CourseService;
import com.learning.coursefun.services.ModuleAssesmentStatusService;
import com.learning.coursefun.services.UserService;
import com.learning.coursefun.util.LoggerUtil;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepo courseRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @Autowired
    ModuleAssesmentStatusService moduleAssesmentStatusService;

    @Autowired
    CourseProgressStatusService courseProgressStatusService;

    @Autowired
    CourseCategoriesRepo courseCategoriesRepo;

//	@Autowired
//	CourseModule courseModule;

//	@Autowired
//	ModuleTopic moduleTopic;
//	
//	@Autowired
//	CourseModuleRepo courseModuleRepo;

    Logger logger = LoggerUtil.getLogger();

    @Override
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    @Override
    public Optional<Course> getCourse(int id) {
        Optional<Course> course = courseRepo.findById(id);
        if (course.get() == null) {
            throw new NoSuchElementException();
        }
        return course;
    }

    @Override
    public Course addCourseWithTime(Course course, int userId) {
        User user = userService.getUserById(userId).get();
        if (!(user.getRole().equalsIgnoreCase("teacher"))) return null;
        course.setCourseDate(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(LocalDateTime.now()));
        course.setCourseBy(userId);
        System.out.println(course);
        return courseRepo.save(course);
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepo.save(course);
    }

    @Override
    public boolean enrollCourse(int userId, int courseId) {
        Course courseRec = this.getCourse(courseId).get();
        User userRec = userService.getUserById(userId).get();

        if (!(userRec.getRole().equalsIgnoreCase("student"))) return false;

        List<Course> courseEnrolledList = userRec.getEnrolledCourse();
        List<Course> courseBoughtList = userRec.getPurchasedCourse();
        boolean courseBought = courseBoughtList.contains(courseRec);
        boolean courseAlreadyExist = false;
        for (Course elem : courseEnrolledList) {
            if (elem.getCourseId() == courseId) {
                courseAlreadyExist = true;
                break;
            }
        }
        if (courseAlreadyExist == true) {
            logger.info("course already enrolled");
            return false;
        }
        if (courseBought == true && courseAlreadyExist == false) {
            logger.info("adding course progress status in userObj");

            CourseProgressStatus courseProgressStatus = new CourseProgressStatus();
            courseProgressStatus.setCompletionPercentage(0);
            courseProgressStatus.setCourseCompleted(false);
            courseProgressStatus.setCourseId(courseId);

//			List<ModuleAssesmentStatus> moduleAssesmentStatusList= moduleAssesmentStatusService.createModuleAssesmentStatus(courseRec.getCourseModule());
//			if(!Objects.nonNull(moduleAssesmentStatusList))
//			courseProgressStatus.setModuleAssesmentStatus(moduleAssesmentStatusList);
//			logger.info(moduleAssesmentStatusList.toString());
            CourseProgressStatus courseProgressStatusRec = courseProgressStatusService.saveCourseProgressStatus(courseProgressStatus);


            if (Objects.nonNull(courseProgressStatusRec))
                userRec.getCourseProgressStatus().add(courseProgressStatusRec);
            logger.info("added course module and assesment progress status in UserObj");
            logger.info(courseProgressStatus.toString());
            //logger.info(courseProgressStatus.getModuleAssesmentStatus().toString());


            userRec.getEnrolledCourse().add(courseRec);
            userService.addUser(userRec);
            logger.info("added course to course enrolled List of user obj");

            courseRec.getEnrolledUser().add(userRec);
            Course resCourse = this.addCourse(courseRec);
            logger.info("added user to course enrolled List of course obj");


            return true;
        }

        return false;
    }

    @Override
    public boolean buyCourse(int userId, int courseId) {
        Course courseRec = this.getCourse(courseId).get();
        User userRec = userService.getUserById(userId).get();
        if (!(userRec.getRole().equalsIgnoreCase("student"))) return false;

        // user object preocess
        List<Course> coursePurchasedByUserList = userRec.getPurchasedCourse();
        boolean courseAlreadyexist = false;
        for (Course elem : coursePurchasedByUserList) {
            if (elem.getCourseId() == courseId) {
                courseAlreadyexist = true;
            }
        }
        if (courseAlreadyexist == false) {
            userRec.getPurchasedCourse().add(courseRec);
            userService.addUser(userRec);// saved user);
            logger.info("added course to user obj coursePurchased List");
            courseRec.getUserBought().add(userRec);// adds user to course list
            Course resCourse = this.addCourse(courseRec);// saved course
            logger.info("added user to course obj userCourseBought list");

            return true;
        }

        return false;
    }

    @Override
    public Course addCourseModuleByCourseId(CourseModule courseModule, int courseId, int userId) {
        // TODO Auto-generated method stub
        Optional<Course> course = this.getCourse(courseId);
        if (course.get().getCourseModule().contains(courseModule)) return null;
        if (course.get().getCourseBy() == userId) {
            course.get().getCourseModule().add(courseModule);
            this.addCourse(course.get());
            return course.get();
        }
        return null;
    }

    @Override
    public Course addCourseFeedbackByCourseId(FeedBack courseFeedback, int courseId, int userId) {
        Optional<Course> course = this.getCourse(courseId);
        courseFeedback.setRatingBy(userId);
        User user = userService.getUserById(userId).get();
        if (!(user.getRole().equalsIgnoreCase("student"))) return null;
        for (FeedBack f : course.get().getFeedBack()) {
            if (f.getRatingBy() == userId) return null;
        }

        course.get().getFeedBack().add(courseFeedback);
        this.addCourse(course.get());
        return course.get();

    }

    @Override
    public Course addTopicToCourseModule(ModuleTopic moduleTopic, int userId, int courseId, int moduleId) {
        Optional<Course> course = this.getCourse(courseId);
        if (!(course.get().getCourseBy() == userId)) return null;
        for (CourseModule courseModule : course.get().getCourseModule()) {
            if (courseModule.getId() == moduleId) {
                if (courseModule.getModuleTopics().contains(moduleTopic)) return null;
                courseModule.getModuleTopics().add(moduleTopic);
            }
        }

        return this.addCourse(course.get());

    }

    @Override
    public boolean deleteCourse(int courseId, int userId) {
        Optional<Course> course = this.getCourse(courseId);
        User user = userService.getUserById(userId).get();
        if (!(user.getRole().equalsIgnoreCase("teacher"))) return false;

        if (course.get().getCourseBy() == userId) {
            courseRepo.delete(course.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCourseModule(int userId, int courseId, int courseModuleId) {
        Optional<Course> c = this.getCourse(courseId);
        if (c.get().getCourseBy() != userId) return false;
        for (CourseModule cm : c.get().getCourseModule()) {
            if (cm.getId() == courseModuleId) {
                c.get().getCourseModule().remove(cm);
                this.addCourse(c.get());
                return true;
            }
        }

        return false;
    }

    @Override
    public Course deleteTopicFromCourseModule(int userId, int courseId, int moduleId, int moduleTopicId) {
//		Optional<Course> course=this.getCourse(courseId);
//		//CourseModule cmObj=new CourseModule();
//		Optional<CourseModule> courseModule= courseModuleRepo.findById(moduleId);
//		
////	   for(CourseModule cm:course.get().getCourseModule()) {
////		   if(cm.getId()==moduleId) {
////			   cm.getModueleTopic().
////		   }
////	   }
//	   for(ModuleTopic mt:courseModule.get().getModueleTopic()) {
//		   if(mt.getId()==moduleTopicId) {
//			   courseModule.get().getModueleTopic().remove(mt);
//			   
//		   }
//	   }
//	   courseModuleRepo.save(courseModule.get());
//	   //course.get().setCou
//	   //add update functinality here
//		return courseRepo.findById(courseId).get();
        return null;
    }

    @Override
    public CourseCategory addCourseCategory(CourseCategory courseCategory) {
        return courseCategoriesRepo.save(courseCategory);

    }

    @Override
    public List<CourseCategory> getAllCourseCategory() {

        return (List<CourseCategory>) courseCategoriesRepo.findAll();
    }

    @Override
    public CourseCategory getCourseCategoryById(int courseCategoryId) {

        return courseCategoriesRepo.findById(courseCategoryId).get();
    }

    @Override
    public boolean removeCourseCategoryById(int courseCategoryId) {

        if (courseCategoryId != 0) {
            courseCategoriesRepo.deleteById(courseCategoryId);
            return true;
        } else return false;

    }

    @Override
    public List<Course> getAllPendingVerificationCourses() {

        List<Course> allPendingCourse = courseRepo.getAllPendingCourse();
        return allPendingCourse;
    }

    @Override
    public List<Course> getAllAcceptedCourses() {
        List<Course> allAcceptedCourses = courseRepo.getAllVerifiedCourses();
        return allAcceptedCourses;
    }

    @Override
    public List<Course> getAllRejectedCourses() {
        List<Course> allRejectedCourses = courseRepo.getAllRejectedCourses();
        return allRejectedCourses;
    }

    @Override
    public Course aprroveCourse(int userId, int courseId) {
        User userRes = userService.getUserById(userId).get();
        if (!(userRes.getRole().equalsIgnoreCase("admin"))) return null;
        Course courseRes = this.getCourse(courseId).get();
        if (courseRes.getCourseVerified().equalsIgnoreCase("accepted") || courseRes.getCourseVerified().equalsIgnoreCase("rejected"))
            return null;
        courseRes.setCourseVerified("accepted");
        courseRes.setCourseVerifiedBy(userId);
        return this.addCourse(courseRes);
    }

    @Override
    public Course rejectCourse(int userId, int courseId) {
        User userRes = userService.getUserById(userId).get();
        if (!(userRes.getRole().equalsIgnoreCase("admin"))) return null;
        Course courseRes = this.getCourse(courseId).get();
        if (courseRes.getCourseVerified().equalsIgnoreCase("accepted") || courseRes.getCourseVerified().equalsIgnoreCase("rejected"))
            return null;
        courseRes.setCourseVerified("rejected");
        courseRes.setCourseVerifiedBy(userId);
        return this.addCourse(courseRes);
    }

    @Override
    public List<Course> enrolledCoursesByUserId(int userId) {

        User user = userRepo.getEnrolledBoughtCoursesOfUser(userId);

        //System.out.println(user);

        if (user == null) return new ArrayList<Course>();
        return user.getEnrolledCourse();
    }

    @Override
    public List<Course> boughtCoursesByUserId(int userId) {


        User user = userRepo.getEnrolledBoughtCoursesOfUser(userId);
        //System.out.println(user.getPurchasedCourse());
        if (user == null) return new ArrayList<Course>();
        return user.getPurchasedCourse();
    }

}
