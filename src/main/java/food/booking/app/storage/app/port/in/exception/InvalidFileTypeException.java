package food.booking.app.storage.app.port.in.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;
import java.util.Iterator;
import java.util.Set;

/**
 * Thrown when file has invalid type
 *
 * @author shazam2morrow
 */
public class InvalidFileTypeException extends ConstraintViolationException {

    private final static String CODE = "file.type.invalid";

    public InvalidFileTypeException(String message, Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(message, constraintViolations);
    }

    /**
     * Invalid file type constraint violation
     *
     * @author shazam2morrow
     */
    @RequiredArgsConstructor
    public static class InvalidFileTypeConstraintViolation implements ConstraintViolation<MultipartFile> {

        private final MultipartFile file;

        @Override
        public String getMessage() {
            return CODE;
        }

        @Override
        public String getMessageTemplate() {
            return "";
        }

        @Override
        public MultipartFile getRootBean() {
            return file;
        }

        @Override
        public Class<MultipartFile> getRootBeanClass() {
            return MultipartFile.class;
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
            return file.getOriginalFilename();
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
