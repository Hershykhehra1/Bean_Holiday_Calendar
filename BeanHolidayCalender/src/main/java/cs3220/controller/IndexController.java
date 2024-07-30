package cs3220.controller;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cs3220.DataComponent;
import cs3220.model.HolidayEntry;

@Controller
public class IndexController {

	@Autowired
	private DataComponent dataComponent;

	@GetMapping("/")
	public String index(Model model) {
		List<HolidayEntry> entries = dataComponent.getEntries();
		entries.sort(Comparator.comparing(HolidayEntry::getDate));
		for (HolidayEntry entry : entries) {
			entry.setFormattedDate(formatDate(entry.getDate()));
		}
		model.addAttribute("entries", entries);
		return "index";
	}

	@GetMapping("/add")
	public String showAddForm(Model model, HolidayEntry entry) {
		model.addAttribute("entry", entry);
		return "AddHoliday";
	}

	@PostMapping("/add")
	public String addHoliday(Model model, @RequestParam String day, @RequestParam String month,
			@RequestParam String year, @RequestParam String name, @Valid @ModelAttribute("entry") HolidayEntry newEntry,
			BindingResult validationResult) {
		String date = year + "-" + month + "-" + day;

		if (validationResult.hasErrors()) {
			return "AddHoliday";
		}

		for (HolidayEntry entry : dataComponent.getEntries()) {
			if (entry.getDate().equals(date) || entry.getName().equals(name)) {
				model.addAttribute("errorMessage", "Holiday Exists");
				return "AddHoliday";
			}
		}

		dataComponent.getEntries().add(new HolidayEntry(date, name));
		Collections.sort(dataComponent.getEntries(), Comparator.comparing(HolidayEntry::getDate));
		return "redirect:/";
	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable int id, Model model) {
		model.addAttribute("entry", dataComponent.getEntryById(id));

		String[] dateParts = dataComponent.getEntryById(id).getDate().split("-");
		model.addAttribute("day", dateParts[2]);
		model.addAttribute("month", dateParts[1]);
		model.addAttribute("year", dateParts[0]);

		return "UpdateHoliday";
	}

	@PostMapping("/update/{id}")
	public String updateHoliday(@RequestParam String day, @RequestParam String month, @RequestParam String year,
			@RequestParam String name, @PathVariable int id, @Valid @ModelAttribute("entry") HolidayEntry newEntry,
			BindingResult validationResult, Model model) {

		if (validationResult.hasErrors()) {
			model.addAttribute("entry", newEntry);
			model.addAttribute("day", day);
			model.addAttribute("month", month);
			model.addAttribute("year", year);
			return "UpdateHoliday";
		}

		String newDate = year + "-" + month + "-" + day;

		for (HolidayEntry existingEntry : dataComponent.getEntries()) {
			if (existingEntry.getId() != id
					&& (existingEntry.getDate().equals(newDate) || existingEntry.getName().equals(name))) {
				model.addAttribute("errorMessage", "Holiday Exists");
				model.addAttribute("entry", newEntry);
				model.addAttribute("day", day);
				model.addAttribute("month", month);
				model.addAttribute("year", year);
				return "UpdateHoliday";
			}
		}

		HolidayEntry entry = getEntryByID(id);
		entry.setDate(newDate);
		entry.setName(name);

		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteHoliday(@PathVariable int id) {
		HolidayEntry entry = null;
		for (HolidayEntry temporary : dataComponent.getEntries()) {
			if (temporary.getId() == id) {
				entry = temporary;
				break;
			}
		}

		if (entry != null) {
			dataComponent.getEntries().remove(entry);
		}
		return "redirect:/";
	}

	private HolidayEntry getEntryByID(int id) {
		for (HolidayEntry entry : dataComponent.getEntries()) {
			if (entry.getId() == id) {
				return entry;
			}
		}
		return null;
	}

	private String formatDate(String date) {
		String yearNum = date.substring(0, 4);
		String monthNum = date.substring(5, 7);
		String dayNum = date.substring(8, 10);

		int year = Integer.parseInt(yearNum);
		int month = Integer.parseInt(monthNum);
		int day = Integer.parseInt(dayNum);

		String monthName = new DateFormatSymbols().getMonths()[month - 1];

		return String.format("%02d %s %d", day, monthName, year);
	}
	
	

}