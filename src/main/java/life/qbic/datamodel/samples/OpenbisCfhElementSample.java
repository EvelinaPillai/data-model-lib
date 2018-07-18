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
 * Class representing a sample created in a sample preparation that will be used to measure data
 * 
 * @author Andreas Friedrich
 *
 */
public class OpenbisCfhElementSample extends AOpenbisSample {

  String Q_CFH_ELEMENTS;
  private String digestion;
  private String elements;
  private String device;

  /**
   * Create a new Test Sample
   * 
   * @param openbisName Code of the sample
   * @param experiment Experiment the sample is attached to
   * @param secondaryName Secondary Name of the sample (e.g. humanly readable identifier)
   * @param additionalNotes Free text notes for the sample
   * @param factors A list of conditions of this sample
   * @param sampleType Measurement type of this sample (e.g. protein)
   * @param parent Extract parent of this sample
   */
  public OpenbisCfhElementSample(String openbisName, String space, String experiment,
	      String secondaryName, String additionalNotes,List<Property> factors, String digestion , String elements , 
	      String device , String parent, String extID) {
	    super(openbisName, space, experiment, secondaryName, additionalNotes, factors ,parent , extID, "Q_CFH_ELEMENTS");
	    this.digestion = digestion;
	    this.elements = elements;
	    this.device = device;
  }

//  public OpenbisCfhElementSample(String openbisName, String space, String experiment,
//	      String secondaryName, String additionalNotes, String Matrix , String Elements , 
//	      String device , String parent, String extID) {
//	      super(openbisName, space, experiment, secondaryName, additionalNotes, factors, parent, extID,
//	        "Q_CFH_ELEMENTS"
//    this.Q_SAMPLE_TYPE = sampleType;
//  }

  public Map<String, String> getValueMap() {
    Map<String, String> res = super.getValueMap();
    res.put("Q_CFH_ELEMENTS", Q_CFH_ELEMENTS); //TODO delete because not needed
    res.put("Q_CFH_DIGESTION", digestion);
    res.put("Q_ELEMENT_DESC", elements);
    res.put("Q_CFH_DEVICES", device);
    
    return res;
  }

}
