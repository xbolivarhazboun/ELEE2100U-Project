public class Capacitor implements Comparable<Capacitor> {
    String datasheetURL;
    String imageURL;
    String dK_Part;
    String manufacturerPartNo;
    String manufacturer;
    String supplier;
    String description;
    String stock;
    double price;
    String atQuantity;
    String minOrderQty;
    String packageType;
    String series;
    String partStatus;
    double capacitance;
    String tolerance;
    double voltage_rated;
    String esr;
    String estimatedDurability;
    String termination;
    String mountingType;
    String package_case;
    String leadSpacing;
    double height;
    String operatingTemp;
    Shape shape;
    double energyCapacity;
    double volume;
    double energyDensity;
    double baseLength;
    double baseWidth;
    double baseRadius;
    double baseArea;

    double energyDensityPerDollar;





    public void initialize() {
        if(shape.equals(Shape.CYLINDER)){
            this.baseRadius= (this.baseLength)/2d;
            this.baseArea = Math.PI*Math.pow(this.baseRadius,2);
        }
        if(shape.equals(Shape.RECTANGLE)){
            this.baseArea = baseLength*baseWidth;
        }
        this.volume = baseArea * height;
        this.energyCapacity = 0.5 * Math.pow(voltage_rated, 2) * capacitance;
        this.energyDensity = this.energyCapacity / this.volume;
        this.energyDensityPerDollar = energyDensity/price;
    }

    @Override
    public int compareTo(Capacitor o) {
        Double energyDensityPerDollarA = this.energyDensityPerDollar;
        Double energyDensityPerDollarB = o.energyDensityPerDollar;
        return energyDensityPerDollarA.compareTo(energyDensityPerDollarB);
    }


    //region Setters And Getter
    public double getEnergyDensityPerDollar() {
        return energyDensityPerDollar;
    }

    public void setEnergyDensityPerDollar(double energyDensityPerDollar) {
        this.energyDensityPerDollar = energyDensityPerDollar;
    }
    public String getDatasheetURL() {
        return datasheetURL;
    }

    public void setDatasheetURL(String datasheetURL) {
        this.datasheetURL = datasheetURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getdK_Part() {
        return dK_Part;
    }

    public void setdK_Part(String dK_Part) {
        this.dK_Part = dK_Part;
    }

    public String getManufacturerPartNo() {
        return manufacturerPartNo;
    }

    public void setManufacturerPartNo(String manufacturerPartNo) {
        this.manufacturerPartNo = manufacturerPartNo;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAtQuantity() {
        return atQuantity;
    }

    public void setAtQuantity(String atQuantity) {
        this.atQuantity = atQuantity;
    }

    public String getMinOrderQty() {
        return minOrderQty;
    }

    public void setMinOrderQty(String minOrderQty) {
        this.minOrderQty = minOrderQty;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getPartStatus() {
        return partStatus;
    }

    public void setPartStatus(String partStatus) {
        this.partStatus = partStatus;
    }

    public double getCapacitance() {
        return capacitance;
    }

    public void setCapacitance(double capacitance) {
        this.capacitance = capacitance;
    }

    public String getTolerance() {
        return tolerance;
    }

    public void setTolerance(String tolerance) {
        this.tolerance = tolerance;
    }

    public double getVoltage_rated() {
        return voltage_rated;
    }

    public void setVoltage_rated(double voltage_rated) {
        this.voltage_rated = voltage_rated;
    }

    public String getEsr() {
        return esr;
    }

    public void setEsr(String esr) {
        this.esr = esr;
    }

    public String getEstimatedDurability() {
        return estimatedDurability;
    }

    public void setEstimatedDurability(String estimatedDurability) {
        this.estimatedDurability = estimatedDurability;
    }

    public String getTermination() {
        return termination;
    }

    public void setTermination(String termination) {
        this.termination = termination;
    }

    public String getMountingType() {
        return mountingType;
    }

    public void setMountingType(String mountingType) {
        this.mountingType = mountingType;
    }

    public String getPackage_case() {
        return package_case;
    }

    public void setPackage_case(String package_case) {
        this.package_case = package_case;
    }

    public String getLeadSpacing() {
        return leadSpacing;
    }

    public void setLeadSpacing(String leadSpacing) {
        this.leadSpacing = leadSpacing;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getOperatingTemp() {
        return operatingTemp;
    }

    public void setOperatingTemp(String operatingTemp) {
        this.operatingTemp = operatingTemp;
    }

    public double getEnergyCapacity() {
        return energyCapacity;
    }

    public void setEnergyCapacity(double energyCapacity) {
        this.energyCapacity = energyCapacity;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getEnergyDensity() {
        return energyDensity;
    }

    public void setEnergyDensity(double energyDensity) {
        this.energyDensity = energyDensity;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public double getBaseLength() {
        return baseLength;
    }

    public void setBaseLength(double baseLength) {
        this.baseLength = baseLength;
    }

    public double getBaseWidth() {
        return baseWidth;
    }

    public void setBaseWidth(double baseWidth) {
        this.baseWidth = baseWidth;
    }

    public double getBaseDiameter() {
        return baseLength;
    }

    public void setBaseDiameter(double baseLength) {
        this.baseLength = baseLength;
    }

    public double getBaseRadius() {
        return baseRadius;
    }

    public void setBaseRadius(double baseRadius) {
        this.baseRadius = baseRadius;
    }

    public double getBaseArea() {
        return baseArea;
    }

    public void setBaseArea(double baseArea) {
        this.baseArea = baseArea;
    }
    //endregion
    @Override
    public String toString() {
        return "Capacitor{" +
                "datasheetURL='" + datasheetURL + '\n' +
                ", imageURL='" + imageURL + '\n' +
                ", dK_Part='" + dK_Part + '\n' +
                ", manufacturerPartNo='" + manufacturerPartNo + '\n' +
                ", manufacturer='" + manufacturer + '\n' +
                ", supplier='" + supplier + '\n' +
                ", description='" + description + '\n' +
                ", stock='" + stock + '\n' +
                ", price=" + price + '\n' +
                ", atQuantity='" + atQuantity + '\n' +
                ", minOrderQty='" + minOrderQty + '\n' +
                ", packageType='" + packageType + '\n' +
                ", series='" + series + '\n' +
                ", partStatus='" + partStatus + '\n' +
                ", capacitance=" + capacitance + '\n' +
                ", tolerance='" + tolerance + '\n' +
                ", voltage_rated=" + voltage_rated + '\n' +
                ", esr='" + esr + '\n' +
                ", estimatedDurability='" + estimatedDurability + '\n' +
                ", termination='" + termination + '\n' +
                ", mountingType='" + mountingType + '\n' +
                ", package_case='" + package_case + '\n' +
                ", leadSpacing='" + leadSpacing + '\n' +
                ", height=" + height + '\n' +
                ", operatingTemp='" + operatingTemp + '\n' +
                ", energyCapacity=" + energyCapacity + '\n' +
                ", volume=" + volume + '\n' +
                ", energyDensity=" + energyDensity + '\n' +
                ", shape=" + shape + '\n' +
                ", baseLength=" + baseLength + '\n' +
                ", baseWidth=" + baseWidth + '\n' +
                ", baseLength=" + baseLength + '\n' +
                ", baseRadius=" + baseRadius + '\n' +
                ", baseArea=" + baseArea + '\n' +
                '}';
    }
}



