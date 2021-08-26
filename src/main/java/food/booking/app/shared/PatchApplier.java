package food.booking.app.shared;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;

/**
 * Patch applier
 *
 * @param <I> input object type
 * @param <O> output object type
 * @author shazam2morrow
 */
@RequiredArgsConstructor
public abstract class PatchApplier<I, O> {

    protected final ObjectMapper objectMapper;

    protected final static String PATCH_TYPE = "application/json-patch+json";

    /**
     * Get output type
     *
     * @return output type
     */
    protected abstract Class<O> getOutputType();

    /**
     * Apply patch
     *
     * @param patch  json patch
     * @param object input object
     * @return output object
     * @throws JsonPatchException      if json patch can not be applied to the given object
     * @throws JsonProcessingException if json can not be processed
     */
    protected O applyPatch(JsonPatch patch, I object) throws JsonPatchException, JsonProcessingException {
        JsonNode jsonNode = objectMapper.convertValue(object, JsonNode.class);
        return objectMapper.treeToValue(patch.apply(jsonNode), getOutputType());
    }

}
