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
 * Class representing a sample created in a sample preparation (in this case
 * NMR) that will be used to measure data
 * 
 *
 */
public class OpenbisCfhNMRSample extends AOpenbisSample {

	String Q_SAMPLE_TYPE;
	private String expType;
	private String expDesc;
	private String solvent;
	private String solventDetails;
	private String concentration;
	private String volume;
	private String pH;
	private String buffer;
	private String date;
	private String storage;
	private String storageDetails;
	private String quantitation;
	private String sampleDesc;
	private String nmrSpec;
	private String probe;
	private String probeDetails;
	private String temperature;

	/**
	 * Create a new NMR Sample
	 * 
	 * @param openbisName     Code of the sample
	 * @param space
	 * @param experiment      Experiment the sample is attached to
	 * @param secondaryName   Secondary Name of the sample (e.g. humanly readable
	 *                        identifier)
	 * @param additionalNotes Free text notes for the sample
	 * @param factors         A list of conditions of this sample
	 * @param parent          Extract parent of this sample
	 * @param extID
	 */
	public OpenbisCfhNMRSample(String openbisName, String space, String experiment, String secondaryName,
			String additionalNotes, List<Property> factors, String sampleType, String expType, String expDesc,
			String solvent, String solventDetails, String concentration, String volume, String pH, String buffer,
			String date, String storage, String storageDetails, String quantitation, String sampleDesc, String nmrSpec,
			String probe, String probeDetails, String temperature, String parent, String extID) {
		super(openbisName, space, experiment, secondaryName, additionalNotes, factors, parent, extID, "Q_CFH_NMR_RUN");
		this.Q_SAMPLE_TYPE = sampleType;
		this.expType = expType;
		this.expDesc = expDesc;
		this.solvent = solvent;
		this.solventDetails = solventDetails;
		this.concentration = concentration;
		this.volume = volume;
		this.pH = pH;
		this.buffer = buffer;
		this.date = date;
		this.storage = storage;
		this.storageDetails = storageDetails;
		this.quantitation = quantitation;
		this.sampleDesc = sampleDesc;
		this.nmrSpec = nmrSpec;
		this.probe = probe;
		this.probeDetails = probeDetails;
		this.temperature = temperature;

	}

	public OpenbisCfhNMRSample(int tempID, List<AOpenbisSample> parents, String secondaryName, String externalID,
			List<Property> newFactors, String additionalNotes) {
		super(tempID, parents, "Q_CFH_NMR_RUN", secondaryName, externalID, newFactors, additionalNotes);
	}

	public Map<String, String> getValueMap() {
		Map<String, String> res = super.getValueMap();
		res.put("Q_SAMPLE_TYPE", Q_SAMPLE_TYPE);
		res.put("NMR_EXP_TYPES", expType);
		res.put("NMR_EXP_DESC", expDesc);
		res.put("NMR_SOLVENT", solvent);
		res.put("NMR_CONCENTRATION", concentration);
		res.put("NMR_VOLUME", volume);
		res.put("NMR_PH", pH);
		res.put("NMR_BUFFER", buffer);
		res.put("NMR_DATE", date);
		res.put("NMR_STORAGE", storage);
		res.put("NMR_QUANTITATION", quantitation);
		res.put("NMR_SAMPLE_DESC", sampleDesc);
		res.put("NMR_SPECTROMETER", nmrSpec);
		res.put("NMR_PROBE", probe);
		res.put("NMR_TEMPERATURE", temperature);
		res.put("NMR_PROBE_DETAILS", probeDetails);
		res.put("NMR_SOLVENT_DETAILS", solventDetails);
		res.put("NMR_STORAGE_DETAILS", storageDetails);

		return res;
	}
}
