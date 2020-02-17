/*******************************************************************************
 * QBiC Project Wizard enables users to create hierarchical experiments including different study
 * conditions using factorial design. Copyright (C) "2016" Andreas Friedrich
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package life.qbic.datamodel.samples;

import java.util.List;
import java.util.Map;

import life.qbic.xml.properties.Property;

/**
 * Class representing a sample created in a sample preparation (in this case Nmin) that will be used to measure data
 * 
 *
 */
public class OpenbisCfhNminSample extends AOpenbisSample {

  String Q_CFH_NMINS;
  String Q_SAMPLE_TYPE;
  private String depth;
  private String bulk_density;
  private String device;
  private String selection;


 /**
  * Create a new Nmin Sample
  * 
  * @param openbisName Code of the sample
  * @param space
  * @param experiment Experiment the sample is attached to
  * @param secondaryName Secondary Name of the sample (e.g. humanly readable identifier)
  * @param additionalNotes Free text notes for the sample
  * @param factors A list of conditions of this sample
  * @param sampleType Measurement type of this sample (e.g. protein)
  * @param depth Depth the soil sample comes from 
  * @param bulk_density Factor that has to be considered in the concentration due to the depth 
  * @param device //TODO maybe no device needed
  * @param parent Extract parent of this sample
  * @param extID
  */
  public OpenbisCfhNminSample(String openbisName, String space, String experiment,
	      String secondaryName, String additionalNotes,List<Property> factors, String sampleType, String depth , String bulk_density , String selection, 
	      String device , String parent, String extID) {
	    super(openbisName, space, experiment, secondaryName, additionalNotes, factors ,parent , extID, "Q_CFH_NMINS");
	    this.depth = depth;
	    this.bulk_density = bulk_density;
	    this.selection = selection;
	    this.device = device;
	    this.Q_SAMPLE_TYPE = sampleType;
  }



  public Map<String, String> getValueMap() {
    Map<String, String> res = super.getValueMap();
    res.put("Q_CFH_NMINS", Q_CFH_NMINS);
    res.put("Q_CFH_NMIN_DEPTH", depth);
    res.put("Q_CFH_NMIN_DENSITY", bulk_density);
    res.put("Q_CFH_NITRATE", selection);
    res.put("Q_SAMPLE_TYPE", Q_SAMPLE_TYPE);
     
    
    return res;
  }

}
