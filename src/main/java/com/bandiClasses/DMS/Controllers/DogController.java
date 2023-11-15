/**
 * 
 */
package com.bandiClasses.DMS.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bandiClasses.DMS.Models.Dog;
import com.bandiClasses.DMS.Models.Trainer;
import com.bandiClasses.DMS.repository.DogRepository;
import com.bandiClasses.DMS.repository.TrainerRepository;

/**
 * @author Pavan Kumar Reddy Kalla
 *Nov 12, 2023
 */
@Controller
public class DogController {
	
	ModelAndView mv = new ModelAndView();
	@Autowired
	DogRepository dogRepo;
	@Autowired
	TrainerRepository trainerRepo;
//	@RequestMapping("dogHome")
//	public String home() {
//		return "home";
//	}
	
	@RequestMapping("dogHome")
	public ModelAndView home() {
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping("add")
	public ModelAndView add() {
		mv.setViewName("addNewDog.html");
		Iterable<Trainer> trainerList = trainerRepo.findAll();
		mv.addObject("trainers",trainerList);
		return mv;
	}

	@RequestMapping("addNewDog")
	public ModelAndView addNewDog(Dog dog, @RequestParam("trainerId")int id) {
		Trainer trainer = trainerRepo.findById(id).orElse(new Trainer());

		dog.setTrainer(trainer);

		dogRepo.save(dog);
		mv.setViewName("home");
		return mv;
	}

	@RequestMapping("addTrainer")
	public ModelAndView addTrainer() {

		mv.setViewName("addNewTrainer");
		return mv;
	}

	@RequestMapping("trainerAdded")
	public ModelAndView addNewTrainer(Trainer trainer) {
		trainerRepo.save(trainer);
		mv.setViewName("home");
		return mv;
	}
	@RequestMapping("viewModifyDelete")
	public ModelAndView viewDogs() {
		mv.setViewName("viewDogs");
		Iterable<Dog> dogList = dogRepo.findAll();
		mv.addObject("dogs", dogList);
		return mv;
		
	}
	
	@RequestMapping("editDog")
	public ModelAndView editDog(Dog dog) {
		dogRepo.save(dog);
		mv.setViewName("editDog");
		return mv;
		
	}
	

}
