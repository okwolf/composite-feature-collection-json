package org;

import java.util.List;

import org.geotools.feature.FeatureCollection;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.wfs.CompositeFeatureCollection;
import org.opengis.feature.simple.SimpleFeatureType;

public class CompositeFeatureCollectionHack extends CompositeFeatureCollection {

  public CompositeFeatureCollectionHack(@SuppressWarnings("rawtypes") final List<FeatureCollection> collections) {
    super(collections);
  }

  @Override
  public SimpleFeatureType getSchema() {
    final SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
    builder.setName("Hack");
    builder.add("geom", Object.class);
    builder.add("hack_attribute_1", String.class);
    // TODO: remove next line to make number of feature attributes match
    builder.add("hack_attribute_2", String.class);
    return builder.buildFeatureType();
  }

}
