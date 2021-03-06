package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private  FilmDAO filmdao;
	
	@RequestMapping(path= {"/", "home.do"})
	public String home() {
		return "WEB-INF/views/index.jsp";
	}
	@RequestMapping(path= {"findFilmById.do"}, method= RequestMethod.GET)
	public ModelAndView findFilmById(@RequestParam("id")String id) {
		ModelAndView mv = new ModelAndView();	
		Integer integerId = new Integer(id);
		Film film = filmdao.findFilmById(integerId);
		if(film==null) {
			mv.setViewName("WEB-INF/views/NoResults.jsp");
			return mv;		}
		mv.addObject("film", film);
		 mv.setViewName("WEB-INF/views/results.jsp");
		return mv;
	}
//	@RequestMapping("GetNumbers.do")
//	  public ModelAndView getNumbers(@RequestParam(value="howmany", defaultValue="6") int count, HttpSession session) {
//	    hopper.reset();
//	    List<String> nums = new ArrayList<>();
//	    for (int i = 0; i < count; i++) {
//	      nums.add(hopper.drawBall().getValue());
//	    }
//	    ModelAndView mv = new ModelAndView();
//	    mv.addObject("listOfNumbers", nums);
//	    // Check in session for history - session.getAttribute(key)
//	    List< List<String> > history = (List< List<String> >) session.getAttribute("lottoHistory");
//	    if(history == null) {
//	      history = new ArrayList<>();
//	      // Put history in session - session.setAttribute(key, value)
//	      session.setAttribute("lottoHistory", history);
//	    }
//	    history.add(nums);
//	    mv.setViewName("WEB-INF/form.jsp");
//	    return mv;
//	  }
}
