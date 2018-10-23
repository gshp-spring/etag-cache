package hello;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DemoController {

	@Autowired
	CustomerRepository customerRepository;

	@Bean
	Filter shallowEtagFilter() {
		return new ShallowEtagHeaderFilter();
	}

	@GetMapping("/records")
	public @ResponseBody Customer get(@RequestParam String name) {
		log.info("Getting Records");
		return customerRepository.findByFirstName(name);
	}

	@PostMapping("/save")
	public void save(@RequestBody Customer cust) {
		log.info("saving record" + cust.firstName);
		customerRepository.save(cust);
	}
	
	@PostMapping("/update")
	public void update(@RequestBody Customer cust) {
		log.info("updating record" + cust.firstName);
		customerRepository.save(cust);
	}

}
