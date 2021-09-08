package food.booking.app.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.MessageInterpolator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.validation.metadata.ConstraintDescriptor;
import java.util.Locale;

/**
 * Validator configuration
 *
 * @author shazam2morrow
 */
@Configuration
class ValidatorConfig {

    @Bean
    @Primary
    LocalValidatorFactoryBean localValidatorFactoryBean(MessageInterpolator messageInterpolator) {
        var bean = new LocalValidatorFactoryBean();
        bean.setMessageInterpolator(messageInterpolator);
        return bean;
    }

    @Bean
    MessageInterpolator messageInterpolator(MessageSource messageSource) {
        return new CustomMessageInterpolator(messageSource);
    }

    private record CustomMessageInterpolator(MessageSource messageSource) implements MessageInterpolator {

        @Override
        public String interpolate(String message, Context context) {
            return messageSource.getMessage(message, new Object[]{}, null);
        }

        @Override
        public String interpolate(String message, Context context, Locale locale) {
            ConstraintDescriptor<?> descriptor = context.getConstraintDescriptor();
            if (descriptor.getAnnotation() instanceof Size size) {
                int min = size.min();
                int max = size.max();
                String msg = size.message();
                return messageSource.getMessage(msg, new Object[]{min, max}, locale);
            } else if (descriptor.getAnnotation() instanceof Max max) {
                long value = max.value();
                String msg = max.message();
                return messageSource.getMessage(msg, new Object[]{value}, locale);
            } else if (descriptor.getAnnotation() instanceof Min min) {
                long value = min.value();
                String msg = min.message();
                return messageSource.getMessage(msg, new Object[]{value}, locale);
            }
            return messageSource.getMessage(message, new Object[]{}, locale);
        }

    }

}
