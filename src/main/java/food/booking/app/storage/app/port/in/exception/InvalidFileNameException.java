package food.booking.app.storage.app.port.in.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;
import java.util.Iterator;
import java.util.Set;

/**
 * Thrown when file name contains invalid characters
 *
 * @author shazam2morrow
 */
public class InvalidFileNameException extends ConstraintViolationException {

    private final static String CODE = "file.name.invalid";

    public InvalidFileNameException(String message, Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(message, constraintViolations);
    }

    /**
     * Invalid file name constraint violation
     *
     * @author shazam2morrow
     */
    @RequiredArgsConstructor
    public static class InvalidFileNameConstraintViolation implements ConstraintViolation<Resource> {

        private final Resource resource;

        @Override
        public String getMessage() {
            return CODE;
        }

        @Override
        public String getMessageTemplate() {
            return "";
        }

        @Override
        public Resource getRootBean() {
            return resource;
        }

        @Override
        public Class<Resource> getRootBeanClass() {
            return Resource.class;
        }

        @Override
        public Object getLeafBean() {
            return null;
        }

        @Override
        public Object[] getExecutableParameters() {
            return new Object[0];
        }

        @Override
        public Object getExecutableReturnValue() {
            return null;
        }

        @Override
        public Path getPropertyPath() {
            return new Path() {
                @Override
                public Iterator<Node> iterator() {
                    return null;
                }
                @Override
                public String toString() {
                    return "file";
                }
            };
        }

        @Override
        public Object getInvalidValue() {
            return resource.getFilename();
        }

        @Override
        public ConstraintDescriptor<?> getConstraintDescriptor() {
            return null;
        }

        @Override
        public <U> U unwrap(Class<U> aClass) {
            return null;
        }

    }

}
