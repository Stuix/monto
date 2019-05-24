package com.pvtgrupp8.monto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

public class TestUtility {

    private ObjectMapper mapper;

    private String [] testStrings = {"Hydrogen", "Helium", "Lithium", "Beryllium", "Boron",
        "Carbon", "Nitrogen","Hydrogen", "Helium", "Lithium", "Beryllium", "Boron", "Carbon", "Nitrogen",
        "Oxygen", "Fluorine", "Neon", "Sodium", "Magnesium", "Aluminum", "Silicon",
        "Phosphorus", "Sulfur", "Chlorine", "Argon", "Potassium", "Calcium",
        "Scandium", "Titanium", "Vanadium", "Chromium", "Manganese", "Iron",
        "Cobalt", "Nickel", "Copper", "Zinc", "Gallium", "Germanium", "Arsenic",
        "Selenium", "Bromine", "Krypton", "Rubidium", "Strontium", "Yttrium",
        "Zirconium", "Niobium", "Molybdenum", "Technetium", "Ruthenium", "Rhodium",
        "Palladium", "Silver", "Cadmium", "Indium", "Tin", "Antimony", "Tellurium",
        "Iodine", "Xenon", "Cesium", "Barium", "Lanthanum", "Cerium",
        "Praseodymium", "Neodymium", "Promethium", "Samarium", "Europium",
        "Gadolinium", "Terbium", "Dysprosium", "Holmium", "Erbium", "Thulium",
        "Ytterbium","Lutetium", "Hafnium", "Tantalum", "Tungsten", "Rhenium",
        "Osmium", "Iridium", "Platinum", "Gold","Mercury","Thallium", "Lead",
        "Bismuth", "Polonium", "Astatine", "Radon", "Francium", "Radium",
        "Actinium", "Thorium", "Protactinium", "Uranium"};


    public TestUtility(ObjectMapper mapper){
        this.mapper = mapper;
    }

    public <T> T fromJsonResult(MvcResult result,
                         Class<T> tClass) throws Exception {
        return this.mapper.readValue(
            result.getResponse().getContentAsString(),
            tClass);
    }
    public byte[] toJson(Object object) throws Exception {
        return this.mapper
            .writeValueAsString(object).getBytes();
    }

    public String getRandomString(){
        int x =(int) (Math.random()*testStrings.length);
        return testStrings[x];
    }
}
