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

import com.apexlegendsat.springmvc.backend.entity.LegendEntity;
import com.apexlegendsat.springmvc.backend.service.LegendService;

@RestController
@RequestMapping(value = "legend")
public class ApiLegendRestController {

	static Logger logger = LogManager.getLogger(ApiLegendRestController.class.getName());

	@Autowired
	private LegendService legendService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<LegendEntity>> listAllLegends() {
		List<LegendEntity> legends = legendService.findAllLegends();
		if (legends.isEmpty()) {
			return new ResponseEntity<List<LegendEntity>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<LegendEntity>>(legends, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<LegendEntity> getLegend(@PathVariable("id") int id) {
		logger.debug("Fetching Legend with id " + id);
		LegendEntity legend = legendService.findById(id);

		if (legend == null) {
			logger.error("Legend with id " + id + " not found");
			return new ResponseEntity<LegendEntity>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<LegendEntity>(legend, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Boolean> createLegend(@RequestBody LegendEntity legend) {
		logger.debug("Creating Legend " + legend.getName());

		if (legendService.doesLegendExist(legend)) {
			logger.error("A Legend with name " + legend.getName() + " already exist");
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		legendService.saveLegend(legend);

		return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<LegendEntity> deleteLegend(@PathVariable("id") int id) {
		logger.debug("Fetching & Deleting Legend with id " + id);

		LegendEntity legendView = legendService.findById(id);
		if (legendView == null) {
			logger.error("Unable to delete. Legend with id " + id + " not found");
			return new ResponseEntity<LegendEntity>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		legendService.deleteLegendById(id);
		return new ResponseEntity<LegendEntity>(HttpStatus.OK);
	}
}