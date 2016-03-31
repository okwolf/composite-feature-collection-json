package org;

import java.util.List;

import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.wfs.CompositeFeatureCollection;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;

public class FeatureGenerator {
  private int counter = 0;

  private SimpleFeatureType getPointFeatureType() {
    final SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
    builder.setName("Point");
    builder.add("geom", Point.class);
    // TODO: remove next line to make number of feature attributes match
    builder.add("id", Integer.class);
    builder.add("point_name", String.class);
    final SimpleFeatureType featureType = builder.buildFeatureType();
    return featureType;
  }

  public SimpleFeature getPointFeature(final Coordinate coordinate, final String name) {
    final GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
    final SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(getPointFeatureType());

    final Point point = geometryFactory.createPoint(coordinate);
    featureBuilder.add(point);
    // TODO: remove next line to make number of feature attributes match
    featureBuilder.add(this.counter++);
    featureBuilder.add(name);
    final SimpleFeature feature = featureBuilder.buildFeature(null);
    return feature;
  }

  public SimpleFeatureCollection getPointFeatureCollection(final List<SimpleFeature> features) {
    return new ListFeatureCollection(getPointFeatureType(), features);
  }

  private SimpleFeatureType getLineFeatureType() {
    final SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
    builder.setName("Line");
    builder.add("geom", LineString.class);
    // FIXME: JSONCompositeFeatureCollection will use hack_attribute_1 instead of this from
    // CompositeFeatureCollectionHack
    builder.add("line_name", String.class);
    final SimpleFeatureType featureType = builder.buildFeatureType();
    return featureType;
  }

  public SimpleFeature getLineFeature(final Coordinate start, final Coordinate end, final String name) {
    final GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
    final SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(getLineFeatureType());

    final LineString line = geometryFactory.createLineString(new Coordinate[] { start, end });
    featureBuilder.add(line);
    featureBuilder.add(name);
    final SimpleFeature feature = featureBuilder.buildFeature(null);
    return feature;
  }

  public SimpleFeatureCollection getLineFeatureCollection(final List<SimpleFeature> features) {
    return new ListFeatureCollection(getLineFeatureType(), features);
  }

  public CompositeFeatureCollection getCompositeFeatureCollection(
      @SuppressWarnings("rawtypes") final List<FeatureCollection> featureCollections) {
    return new CompositeFeatureCollection(featureCollections);
  }

  public CompositeFeatureCollection getCompositeFeatureCollectionHack(
      @SuppressWarnings("rawtypes") final List<FeatureCollection> featureCollections) {
    return new CompositeFeatureCollectionHack(featureCollections);
  }

}
