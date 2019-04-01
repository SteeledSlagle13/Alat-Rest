package com.apexlegendsat.springmvc.backend.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apexlegendsat.springmvc.backend.service.LegendService;
import com.apexlegendsat.springmvc.backend.view.LegendView;

@RestController
@RequestMapping(value = "legend")
public class ApiLegendRestController {
	
	static Logger logger = LogManager.getLogger(ApiLegendRestController.class.getName());

	@Autowired
	private LegendService legendService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<LegendView>> listAllLegends() {
		List<LegendView> legends = legendService.findAllLegends();
		if (legends.isEmpty()) {
			return new ResponseEntity<List<LegendView>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<LegendView>>(legends, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<LegendView> getLegend(@PathVariable("id") int id) {
		logger.debug("Fetching Legend with id " + id);
		LegendView legend = legendService.findById(id);
		if (legend == null) {
			logger.error("Legend with id " + id + " not found");
			return new ResponseEntity<LegendView>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<LegendView>(legend, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Void> createLegend(@RequestBody LegendView legendView) {
		logger.debug("Creating Legend " + legendView.getName());

		if (legendService.doesLegendExist(legendView)) {
			logger.error("A Legend with name " + legendView.getName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		legendService.saveLegend(legendView);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<LegendView> deleteLegend(@PathVariable("id") int id) {
		logger.debug("Fetching & Deleting Legend with id " + id);

		LegendView legendView = legendService.findById(id);
		if (legendView == null) {
			logger.error("Unable to delete. Legend with id " + id + " not found");
			return new ResponseEntity<LegendView>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		legendService.deleteLegendById(id);
		return new ResponseEntity<LegendView>(HttpStatus.OK);
	}
}