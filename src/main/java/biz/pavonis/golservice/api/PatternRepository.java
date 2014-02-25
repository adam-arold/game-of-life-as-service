package biz.pavonis.golservice.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import biz.pavonis.golservice.api.exception.InvalidIdException;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Contains the patterns which can be used by the {@link GameOfLifeService}.
 * 
 * @author edem
 */
public enum PatternRepository {

    INSTANCE;

    private final List<Pattern> patterns;

    private PatternRepository() {
        patterns = loadPatterns();
    }

    private List<Pattern> loadPatterns() {
        List<Pattern> patterns = new ArrayList<>();
        URL url = Thread.currentThread().getContextClassLoader().getResource("patterns");
        try {
            File patternsFolder = new File(url.toURI());
            GsonBuilder builder = new GsonBuilder();
            Type collectionType = new TypeToken<boolean[][]>() {
            }.getType();
            int id = 1;
            for (File file : patternsFolder.listFiles()) {
                try (Reader reader = new BufferedReader(new FileReader(file))) {
                    boolean[][] patternDescriptor = builder.create().fromJson(reader, collectionType);
                    String patternName = file.getName().replace(".json", "");
                    patterns.add(new Pattern(id, patternName, patternDescriptor));
                    id++;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return patterns;
    }

    /**
     * Returns all patterns.
     * 
     * @return
     */
    public List<Pattern> getPatterns() {
        return patterns;
    }

    /**
     * Returns a {@link Map} of pattern id-name pairs.
     * The {@link Map} returned is in fact a {@link LinkedHashMap}.
     * 
     * @return
     */
    public Map<Integer, String> getPatternNames() {
        Map<Integer, String> result = new LinkedHashMap<>();
        for (Pattern pattern : patterns) {
            result.put(pattern.getId(), pattern.getName());
        }
        return result;
    }

    /**
     * Returns a {@link Pattern} by its id.
     * 
     * @param id
     * @return
     */
    public Pattern getPatternById(int id) {
        Pattern pattern = null;
        try {
            pattern = patterns.get(id - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIdException("There was no pattern corresponding to the supplied id: " + id, e);
        }
        return pattern;
    }
}
