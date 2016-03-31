package org;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.FeatureCollection;
import org.geotools.geojson.feature.FeatureJSON;
import org.geotools.wfs.CompositeFeatureCollection;
import org.junit.Before;
import org.junit.Test;
import org.opengis.feature.simple.SimpleFeature;

import com.vividsolutions.jts.geom.Coordinate;

public class FeatureGeneratorTest {
  private static final Coordinate START            = new Coordinate(0, 0);
  private static final Coordinate END              = new Coordinate(90, 90);
  private static final Coordinate START_TWO        = new Coordinate(45, 45);
  private static final Coordinate END_TWO          = new Coordinate(180, 180);
  private static final String     START_POINT_NAME = "Starting Point";
  private static final String     END_POINT_NAME   = "Ending Point";
  private static final String     LINE_NAME        = "Test Line";
  private static final String     LINE_TWO_NAME    = "Test Line 2";

  private FeatureGenerator generator;
  private FeatureJSON      json;
  private PrintStream      out;

  @Before
  public void setUp() throws Exception {
    this.generator = new FeatureGenerator();
    this.json = new FeatureJSON();
    this.out = System.out;
  }

  private String getCallingMethod() {
    return Thread.currentThread().getStackTrace()[3].getMethodName();
  }

  private void writeFeature(final SimpleFeature feature) throws IOException {
    this.out.println(getCallingMethod() + " Feature:");
    this.json.writeFeature(feature, this.out);
    this.out.println();
  }

  private void writeFeatureCollection(final SimpleFeatureCollection features) throws IOException {
    this.out.println(getCallingMethod() + " Feature Collection:");
    this.json.writeFeatureCollection(features, this.out);
    this.out.println();
  }

  private SimpleFeature getStartPointFeature() {
    return this.generator.getPointFeature(START, START_POINT_NAME);
  }

  private SimpleFeature getEndPointFeature() {
    return this.generator.getPointFeature(END, END_POINT_NAME);
  }

  private SimpleFeatureCollection getPointFeatureCollection() {
    return this.generator.getPointFeatureCollection(Arrays.asList(getStartPointFeature(), getEndPointFeature()));
  }

  private SimpleFeature getLineFeature() {
    return this.generator.getLineFeature(START, END, LINE_NAME);
  }

  private SimpleFeature getLineTwoFeature() {
    return this.generator.getLineFeature(START_TWO, END_TWO, LINE_TWO_NAME);
  }

  private SimpleFeatureCollection getLineFeatureCollection() {
    return this.generator.getLineFeatureCollection(Arrays.asList(getLineFeature(), getLineTwoFeature()));
  }

  @SuppressWarnings("rawtypes")
  private List<FeatureCollection> getFeatureCollections() {
    return Arrays.asList(getPointFeatureCollection(), getLineFeatureCollection());
  }

  private CompositeFeatureCollection getPointCompositeFeatureCollection() {
    return this.generator.getCompositeFeatureCollection(Arrays.asList(getPointFeatureCollection()));
  }

  private CompositeFeatureCollection getLineCompositeFeatureCollection() {
    return this.generator.getCompositeFeatureCollection(Arrays.asList(getLineFeatureCollection()));
  }

  private CompositeFeatureCollection getPointAndLineCompositeFeatureCollection() {
    return this.generator.getCompositeFeatureCollection(getFeatureCollections());
  }

  private CompositeFeatureCollection getPointAndLineCompositeFeatureCollectionHack() {
    return this.generator.getCompositeFeatureCollectionHack(getFeatureCollections());
  }

  private CompositeFeatureCollection getPointCompositeFeatureCollectionHack() {
    return this.generator.getCompositeFeatureCollectionHack(Arrays.asList(getPointFeatureCollection()));
  }

  private CompositeFeatureCollection getLineCompositeFeatureCollectionHack() {
    return this.generator.getCompositeFeatureCollectionHack(Arrays.asList(getLineFeatureCollection()));
  }

  @Test
  public void testGetPointFeature() throws Exception {
    writeFeature(getStartPointFeature());
  }

  @Test
  public void testGetLineFeature() throws Exception {
    writeFeature(getLineFeature());
  }

  @Test
  public void testGetPointFeatureCollection() throws Exception {
    writeFeatureCollection(getPointFeatureCollection());
  }

  @Test
  public void testGetLineFeatureCollection() throws Exception {
    writeFeatureCollection(getLineFeatureCollection());
  }

  @Test
  public void testGetPointCompositeFeatureCollection() throws Exception {
    writeFeatureCollection(getPointCompositeFeatureCollection());
  }

  @Test
  public void testGetLineCompositeFeatureCollection() throws Exception {
    writeFeatureCollection(getLineCompositeFeatureCollection());
  }

  @Test
  public void testGetPointAndLineCompositeFeatureCollection() throws Exception {
    writeFeatureCollection(getPointAndLineCompositeFeatureCollection());
  }

  @Test
  public void testGetPointCompositeFeatureCollectionHack() throws Exception {
    writeFeatureCollection(getPointCompositeFeatureCollectionHack());
  }

  @Test
  public void testGetLineCompositeFeatureCollectionHack() throws Exception {
    writeFeatureCollection(getLineCompositeFeatureCollectionHack());
  }

  @Test
  public void testGetPointAndLineCompositeFeatureCollectionHack() throws Exception {
    writeFeatureCollection(getPointAndLineCompositeFeatureCollectionHack());
  }

}
