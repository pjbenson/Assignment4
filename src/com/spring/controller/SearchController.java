package com.spring.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.SearchStockForm;
import com.spring.model.Stock;
import com.spring.service.StockService;
import com.spring.service.UserService;

@Controller
@SessionAttributes({"sortedStock", "manufacturers"})
public class SearchController {

	@Autowired
	private UserService userService;
	@Autowired
	private StockService stockService;

	@RequestMapping(value="/searchbyAscOrDesc", method = RequestMethod.POST)
	public ModelAndView searchStockByASCorDESC(@ModelAttribute("ascOrDesc") SearchStockForm ssf, ModelMap model){
		List<Stock> stock = getOrderedStock(ssf.getSearchBy(), ssf.getSearchString());
		model.addAttribute("sortedStock", stock);
		return new ModelAndView("redirect:/profile.html");
	}

	@RequestMapping(value="/searchObject", method = RequestMethod.POST)
	public ModelAndView searchStock(@ModelAttribute("searchObject") SearchStockForm ssf, ModelMap model) throws IOException{
		List<Stock> stock = getStock(ssf.getSearchBy(), ssf.getSearchString());
		model.put("unSortedStock", stock);
		return new ModelAndView("redirect:/profile.html");
	}

	private List<Stock> getOrderedStock(String searchType, String ascOrDesc){
		List<Stock> stock = new ArrayList<Stock>();

		for(Stock s: stockService.getAllStock()){
			if(searchType.equals("manufacturer")){
				if(ascOrDesc.equals("ascending")){
					stock.add(s);
					Collections.sort(stock, new ManufacturerComparator());
				}
				else{
					stock.add(s);
					Collections.sort(stock, new ManufacturerComparator());
					Collections.reverse(stock);
				}
			}
			if(searchType.equals("title")){
				if(ascOrDesc.equals("ascending")){
					stock.add(s);
					Collections.sort(stock, new TitleComparator());
				}
				else{
					stock.add(s);
					Collections.sort(stock, new TitleComparator());
					Collections.reverse(stock);
				}
			}
			if(searchType.equals("category")){
				if(ascOrDesc.equals("ascending")){
					stock.add(s);
					Collections.sort(stock, new CategoryComparator());
				}
				else{
					stock.add(s);
					Collections.sort(stock, new CategoryComparator());
					Collections.reverse(stock);
					
				}
			}
			if(searchType.equals("price")){
				if(ascOrDesc.equals("ascending")){
					stock.add(s);
					Collections.sort(stock, new PriceComparator());
				}
				else{
					stock.add(s);
					Collections.sort(stock, new PriceComparator());
					Collections.reverse(stock);
				}
			}
		}
		return stock;
	}

	private List<Stock> getStock(String searchType, String searchString){
		List<Stock> stock = new ArrayList<Stock>();

		for(Stock s: stockService.getAllStock()){
			if(searchType.equals("manufacturer")){
				if(s.getManufacturer().equalsIgnoreCase(searchString)){
					stock.add(s);
				}
			}
			if(searchType.equals("title")){
				if(s.getTitle().equalsIgnoreCase(searchString)){
					stock.add(s);
				}
			}
			if(searchType.equals("category")){
				if(s.getCategory().getCategoryTitle().equalsIgnoreCase(searchString)){
					stock.add(s);
				}
			}
		}
		return stock;
	}

	class CategoryComparator implements Comparator<Stock>{

		@Override
		public int compare(Stock s1, Stock s2) {
			return s1.getCategory().getCategoryTitle().compareTo(s2.getCategory().getCategoryTitle());
		}

	}

	class TitleComparator implements Comparator<Stock>{

		@Override
		public int compare(Stock s1, Stock s2) {
			return s1.getTitle().compareTo(s2.getTitle());
		}

	}

	class ManufacturerComparator implements Comparator<Stock>{

		@Override
		public int compare(Stock s1, Stock s2) {
			return s1.getManufacturer().compareTo(s2.getManufacturer());
		}

	}
	
	class PriceComparator implements Comparator<Stock> {

		@Override
		public int compare(Stock s1, Stock s2) {
			if (s1.getPrice() < s2.getPrice()) return -1;
	        if (s1.getPrice() > s2.getPrice()) return 1;
	        return 0;
		}
	       
	}

}
