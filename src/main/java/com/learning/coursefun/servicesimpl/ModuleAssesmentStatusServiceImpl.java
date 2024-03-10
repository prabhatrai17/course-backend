package com.learning.coursefun.servicesimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.coursefun.custom.exception.GlobalException;
import com.learning.coursefun.entities.Assesment;
import com.learning.coursefun.entities.CourseModule;
import com.learning.coursefun.entities.CourseProgressStatus;
import com.learning.coursefun.entities.ModuleAssesmentStatus;
import com.learning.coursefun.entities.User;
import com.learning.coursefun.repo.ModuleAssesmentStatusRepo;
import com.learning.coursefun.services.ModuleAssesmentStatusService;
import com.learning.coursefun.services.UserService;
import com.learning.coursefun.util.LoggerUtil;

@Service
public class ModuleAssesmentStatusServiceImpl implements ModuleAssesmentStatusService {
	
	@Autowired
	ModuleAssesmentStatusRepo moduleAssesmentStatusRepo;
	
	@Autowired
	UserService userService;
	
	Logger logger=LoggerUtil.getLogger();

	@Override
	public  List<ModuleAssesmentStatus> createModuleAssesmentStatus(List<CourseModule> courseModuleList) {
		// TODO Auto-generated method stub
		List<ModuleAssesmentStatus> moduleAssesmentStatusList= new ArrayList<>();
		if(courseModuleList.size()!=0||Objects.nonNull(courseModuleList))
		for(CourseModule courseModule:courseModuleList) {
		     ModuleAssesmentStatus moduleAssesmentStatus= new ModuleAssesmentStatus();
		     moduleAssesmentStatus.setModuleId(courseModule.getId());
		     if(Objects.nonNull(courseModule.getAssesment()))
		     moduleAssesmentStatus.setAssesmentId(courseModule.getAssesment().getId());;
		     
		     moduleAssesmentStatusRepo.save(moduleAssesmentStatus);
		     
		     
		     moduleAssesmentStatusList.add(moduleAssesmentStatus);
			
		}
		
		return moduleAssesmentStatusList;
	}

	@Override
	public List<ModuleAssesmentStatus> updateModuleAssesmentStatus(Assesment assesment, int moduleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModuleAssesmentStatus startModuleAssesment(int userId, int courseId, int moduleId, int assesmentId) {
		// TODO Auto-generated method stub
		
		User userRes=userService.getUserById(userId).get();
		List<ModuleAssesmentStatus> moduleAssesmentStatusList =new ArrayList<>();
		CourseProgressStatus courseProgressStatus=new CourseProgressStatus();
		List<CourseProgressStatus> courseProgressStatusList=userRes.getCourseProgressStatus();
		if(Objects.nonNull(courseProgressStatusList))
			for(CourseProgressStatus courseProgressStatusElem:courseProgressStatusList) {
				if(courseProgressStatus.getCourseId()==courseId) {
					courseProgressStatus=courseProgressStatusElem;
					moduleAssesmentStatusList=courseProgressStatus.getModuleAssesmentStatus();
					break;
				}
			}
		
		ModuleAssesmentStatus moduleAssesmentStatus= new ModuleAssesmentStatus();
		moduleAssesmentStatus.setAssesmentId(assesmentId);
		moduleAssesmentStatus.setModuleId(moduleId);
		
		if(Objects.nonNull(moduleAssesmentStatusList))
			moduleAssesmentStatusList.add(moduleAssesmentStatus);
		logger.info("module assesment status added to its list");
		for(ModuleAssesmentStatus moduleAssesmentStatusElem:moduleAssesmentStatusList) {
			if(moduleAssesmentStatusElem.getModuleId()==moduleId) {
				throw new GlobalException("course module assesment Already enrolled"); 
			}
			
		}
		courseProgressStatus.setModuleAssesmentStatus(moduleAssesmentStatusList);
		//add course progerss to list and save list
		userRes.setCourseProgressStatus(courseProgressStatusList);
    return null;
	
	}

}
