package org.proj.app.rest;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.proj.app.config.RedditConn;
import org.proj.app.domain.Image;
import org.proj.app.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api")
public class ImageRestController {
	@Autowired
	private ImageRepository imageRepository;

	@RequestMapping(value = "/images", method = RequestMethod.GET)
	public List<Image> address() {
		return imageRepository.findAll();
	}

	@RequestMapping(value = "/images/{id}", method = RequestMethod.GET)
	public ResponseEntity<Image> addressById(@PathVariable Long id) {
		Image address = imageRepository.findOne(id);
		if (address == null) {
			return new ResponseEntity<Image>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Image>(address, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/images/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Image> deleteAddress(@PathVariable int id) {
		Image address = imageRepository.findById(id);
		if (address == null) {
			return new ResponseEntity<Image>(HttpStatus.NO_CONTENT);
		} else {
			imageRepository.delete(address);
			return new ResponseEntity<Image>(address, HttpStatus.OK);
		}

	}
	@RequestMapping(value = "/images", method = RequestMethod.DELETE)
	public void deleteAll() {
		  imageRepository.deleteAll();

	}
	@RequestMapping(value = "/images/", method = RequestMethod.GET)
	public Set<Image> loadImagesFromSub(@RequestParam("url") String url, @RequestParam("q") String q) throws Exception {
		Set<Image> set;
		Set<Image> fetchedSub;
		RedditConn redditConn = new RedditConn();
			fetchedSub = redditConn.readUrl(url, q);

			try {
				set = new LinkedHashSet<>(imageRepository.findAll());
				set.addAll(fetchedSub);
				imageRepository.save(set);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return fetchedSub;
	}
	@RequestMapping(value = "/images", method = RequestMethod.POST)
	public ResponseEntity<Image> createAddress(@RequestBody Image address) {
		return new ResponseEntity<Image>(imageRepository.save(address), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/images", method = RequestMethod.PUT)
	public Image updateAddress(@RequestBody Image address) {
		return imageRepository.save(address);
	}

}
