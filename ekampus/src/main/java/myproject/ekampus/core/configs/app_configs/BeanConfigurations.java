package myproject.ekampus.core.configs.app_configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanConfigurations {

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
