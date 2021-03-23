import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner; // Import the Scanner class to read text files

import static java.lang.System.exit;

public class ParseCapacitors {
    public static void main(String[] args) {
        final double MAX_CROSS_SECTIONAL_DIAMETER = 50; //
        final int MAX_DESIRED_RESULTS = -1; // (-1 means all) Number of results to put into CSV file.
        ArrayList<Capacitor> capacitors = new ArrayList<Capacitor>();
        parseVendorList(capacitors);

        ArrayList<Capacitor> capacitorsThatFit = new ArrayList<Capacitor>();
        capacitors.forEach(capacitor -> {
            if(capacitor.baseLength<MAX_CROSS_SECTIONAL_DIAMETER&&capacitor.baseWidth<MAX_CROSS_SECTIONAL_DIAMETER){
                capacitorsThatFit.add(capacitor);
            }
        });
        System.out.println("There are " + capacitorsThatFit.size() + " capacitors that meet the requirements.");
        Collections.sort(capacitorsThatFit);
        Collections.reverse(capacitorsThatFit);
        outputToFile(capacitorsThatFit,MAX_DESIRED_RESULTS,MAX_CROSS_SECTIONAL_DIAMETER);
    }



    private static void parseVendorList(ArrayList<Capacitor> capacitors) {
        try {
            File myObj = new File("C:\\Users\\Tex\\IdeaProjects\\superCap\\src\\vendorList2-1.csv");
            Scanner myReader = new Scanner(myObj);
            myReader.nextLine();
            while (myReader.hasNextLine()){
                String rawData = myReader.nextLine();
                String data = rawData.replace("\"","");
                String currentLineData[] = data.split(",");
                if(!allValuesInTableExist(currentLineData) || currentLineData[8].equals("Active")){
                    continue;
                }
                // Parse Voltage
                double voltage = formatAsNumber(currentLineData[16],"voltage");
                // Parse Capacitance
                double capacitance = formatAsNumber(currentLineData[14],"capacitance");
                // Determine Shape
                Shape shape = Shape.extrapolateShape(currentLineData[23]);
                // Parse Voltage
                //System.out.println(rawData);

                String heights[] =currentLineData[24].split(" ");
                double metricHeight = formatAsNumber(heights[1],"measurement");




                Capacitor cap = new Capacitor();
                capacitors.add(cap);
                cap.setDatasheetURL(currentLineData[0]);
                cap.setImageURL(currentLineData[1]);
                cap.setdK_Part(currentLineData[2]);
                cap.setManufacturerPartNo(currentLineData[3]);
                cap.setManufacturer(currentLineData[4]);
                cap.setSupplier(currentLineData[5]);
                cap.setDescription(currentLineData[6]);
                cap.setStock(currentLineData[7]);
                cap.setPrice(Double.parseDouble(currentLineData[8]));
                cap.setAtQuantity(currentLineData[9]);
                cap.setMinOrderQty(currentLineData[10]);
                cap.setPackageType(currentLineData[11]);
                cap.setSeries(currentLineData[12]);
                cap.setPartStatus(currentLineData[13]);
                cap.setCapacitance(capacitance);
                cap.setTolerance(currentLineData[15]);
                cap.setVoltage_rated(voltage);
                cap.setEsr(currentLineData[17]);
                cap.setEstimatedDurability(currentLineData[18]);
                cap.setTermination(currentLineData[19]);
                cap.setMountingType(currentLineData[20]);
                cap.setPackage_case(currentLineData[21]);
                cap.setLeadSpacing(currentLineData[22]);
                cap.setHeight(metricHeight);
                cap.setOperatingTemp(currentLineData[25]);
                cap.setShape(shape);

                if(shape.equals(Shape.RECTANGLE)) {
                    String splitDimensions[] = currentLineData[23].split("W");
                    String metricDimensions = splitDimensions[1];
                    String sides[] = metricDimensions.split("x");
                    double length = formatAsNumber(sides[0],"measurement");
                    double width = formatAsNumber(sides[1],"measurement");
                    cap.setBaseWidth(width);
                    cap.setBaseLength(length);

                }
                else if (shape.equals(Shape.CYLINDER)){
                    String splitDimensions[]=currentLineData[23].split("a ");
                    String metricDimensions = splitDimensions[1];
                    double diameter = formatAsNumber(metricDimensions,"measurement");
                    cap.setBaseDiameter(diameter);

                }
                cap.initialize();

            }
            myReader.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        System.out.println(capacitors.size() + " capacitors have being analyzed.");
    }

    public static void outputToFile(ArrayList<Capacitor> capacitors,int maximumDesiredResults,double maxDiameter) {
        int max;
        ArrayList<Capacitor> capacitorBank= new ArrayList<Capacitor>();
//        for (Capacitor capacitor : capacitors) {
//            if(capacitor.getBaseRadius()*4<maxDiameter){
//                System.out.println("Our capacitors can be put together vertically");
//                Capacitor capBank = new Capacitor();
//                String DatasheetURL;
//                String ImageURL;
//                String DK_Part;
//                String ManufacturerPartNo;
//                String Manufacturer;
//                String Supplier;
//                String Description;
//                String Stock;
//                double Price;
//                String AtQuantity;
//                String MinOrderQty;
//                String PackageType;
//                String Series;
//                String PartStatus;
//                double Capacitance;
//                String Tolerance;
//                double Voltage_rated;
//                String Esr;
//                String EstimatedDurability;
//                String Termination;
//                String MountingType;
//                String Package_case;
//                String LeadSpacing;
//                double Height;
//                String OperatingTemp;
//                Shape  Shape;
//                double EnergyCapacity;
//                double Volume;
//                double EnergyDensity;
//                double BaseLength;
//                double BaseWidth;
//                double BaseRadius;
//                double BaseArea;
//
//            }
//        }
        if(maximumDesiredResults==-1){
            max = capacitors.size();
        }
        else{
            max=maximumDesiredResults;
        }
        try {
                String stringToAdd = "Manufacturer,Manufacturer Part Number,Shape[mm],Length[mm],Width[mm],Radius[mm],Height[mm],Volume[mm^3],Capacitance[Farad],Rated Voltage[Volts],Energy Capacity[Joules], Energy Capacity Density [Joules/mm^3], Price[CAD($)], DigiKey Part Number, Energy Density per Dollar.";
                FileWriter myWriter = new FileWriter("filteredVendorList.csv");
                for (int i = 0; i < max; i++) {
                    Capacitor cap=capacitors.get(i);
                    if(cap.getShape().equals(Shape.RECTANGLE)){
//                        stringToAdd = stringToAdd+"\n"+
//                                cap.getManufacturer() +","+
//                                cap.getManufacturerPartNo() + "," +
//                                cap.getShape() + "," +
//                                cap.getBaseLength() + "," +
//                                cap.getBaseWidth() + "," +
//                                "N/A [Cap is Rectangular]" + "," +
//                                cap.getHeight() + "," +
//                                cap.getVolume() + "," +
//                                cap.getCapacitance() + "," +
//                                cap.getVoltage_rated() + "," +
//                                cap.getEnergyCapacity() + "," +
//                                cap.getEnergyDensity() + "," +
//                                cap.getPrice();
                    }
                    else
                    {
                        stringToAdd = stringToAdd+"\n"+
                                cap.getManufacturer() +","+
                                cap.getManufacturerPartNo() + "," +
                                cap.getShape() + "," +
                                "N/A [Cap is Cylindical]" + "," +
                                "N/A [Cap is Cylindical]" + "," +
                                cap.getBaseRadius() + "," +
                                cap.getHeight() + "," +
                                cap.getVolume() + "," +
                                cap.getCapacitance() + "," +
                                cap.getVoltage_rated() + "," +
                                cap.getEnergyCapacity() + "," +
                                cap.getEnergyDensity() + "," +
                                cap.getPrice()+ "," +
                                cap.getdK_Part()+ "," +
                                cap.getEnergyDensityPerDollar();
                    }


                }
                myWriter.write(stringToAdd);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

    private static boolean isNumber(String str){
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public static double formatAsNumber(String str) {
        return Double.parseDouble(str.replaceAll("[^0-9]", ""));
    }
    public static double formatAsNumber(String str, String parameterType) {
        if(parameterType.equals("capacitance")){
            if(str.contains("F")){
                str = str.replaceAll("F","");
                if(str.contains("m")){
                    str = str.replaceAll("m","");
                    return Double.parseDouble(str)/1000d;
                }
                else{
                    return Double.parseDouble(str);
                }
            }
        }
        if(parameterType.equals("measurement")){
            str = str.replaceAll("m","");
            str = str.replaceAll("\\(","");
            str = str.replaceAll("\\)","");
            return Double.parseDouble(str);
        }
        if(parameterType.equals("voltage")){
            str = str.replaceAll("V","");
            str = str.replaceAll("\\(","");
            str = str.replaceAll("\\)","");
            return Double.parseDouble(str);
        }
        return Double.parseDouble(str.replaceAll("[^0-9]", ""));
    }
    private static boolean allValuesInTableExist(String currentCapacitor[]) {
        ArrayList<Parameter> parameters = new ArrayList<Parameter>();

        Parameter DatasheetURL = new Parameter(0, true);
        Parameter ImageURL = new Parameter(1, false);
        Parameter dK_Part = new Parameter(2, true);
        Parameter ManufacturerPartNo = new Parameter(3, false);
        Parameter Manufacturer = new Parameter(4, false);
        Parameter Supplier = new Parameter(5, false);
        Parameter Description = new Parameter(6, false);
        Parameter Stock = new Parameter(7, false);
        Parameter Price = new Parameter(8, true);
        Parameter AtQuantity = new Parameter(9, false);
        Parameter MinOrderQty = new Parameter(10, false);
        Parameter PackageType = new Parameter(11, false);
        Parameter Series = new Parameter(12, false);
        Parameter PartStatus = new Parameter(13, false);
        Parameter Capacitance = new Parameter(14, true);
        Parameter Tolerance = new Parameter(15, false);
        Parameter Voltage_rated = new Parameter(16, true);
        Parameter Esr = new Parameter(17, true);
        Parameter EstimatedDurability = new Parameter(18, false);
        Parameter Termination = new Parameter(19, false);
        Parameter MountingType = new Parameter(20, false);
        Parameter Package_case = new Parameter(21, false);
        Parameter LeadSpacing = new Parameter(22, false);
        Parameter BaseArea = new Parameter(23, true);
        Parameter Height = new Parameter(24, true);
        Parameter OperatingTemp = new Parameter(25, false);

        parameters.add(DatasheetURL);
        parameters.add(ImageURL);
        parameters.add(dK_Part);
        parameters.add(ManufacturerPartNo);
        parameters.add(Manufacturer);
        parameters.add(Supplier);
        parameters.add(Description);
        parameters.add(Stock);
        parameters.add(Price);
        parameters.add(AtQuantity);
        parameters.add(MinOrderQty);
        parameters.add(PackageType);
        parameters.add(Series);
        parameters.add(PartStatus);
        parameters.add(Capacitance);
        parameters.add(Tolerance);
        parameters.add(Voltage_rated);
        parameters.add(Esr);
        parameters.add(EstimatedDurability);
        parameters.add(Termination);
        parameters.add(MountingType);
        parameters.add(Package_case);
        parameters.add(LeadSpacing);
        parameters.add(BaseArea);
        parameters.add(Height);
        parameters.add(OperatingTemp);


        for (Parameter parameter : parameters) {
            if (parameter.isEssentialData) {
                if (currentCapacitor[parameter.column].equals("-")) {
                    return false;
                }
            }
        }
        return true;
    }
}
