package by.khrapovitsky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorsController {
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		model.setViewName("errors/403");
		return model;

	}
	
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public ModelAndView notFound() {

		ModelAndView model = new ModelAndView();

		model.setViewName("errors/404");
		return model;

	}
}
