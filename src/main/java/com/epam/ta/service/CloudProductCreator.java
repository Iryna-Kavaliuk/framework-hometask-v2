package com.epam.ta.service;

import com.epam.ta.model.CloudProduct;

public class CloudProductCreator {

    public static final String COMPUTE_ENGINE_AREA = "testdata.product.compute-engine";
    public static final String OPERATING_SYSTEM = "testdata.product.os";

    private static final int NUMBER_OF_INSTANCES = 4;
    private static final String PROVISIONING_MODEL = "Regular";
    private static final String SERIES = "N1";
    private static final String MACHINE_TYPE = "n1-standard-8";
    private static final String GPU_TYPE = "NVIDIA Tesla T4";
    private static final int NUMBER_OF_GPUS = 1;
    private static final String LOCAL_SSD = "2x375 GB";
    private static final String DATACENTER_LOCATION = "Frankfurt (europe-west3)";
    private static final String COMMITTED_USAGE = "1 Year";

    public static CloudProduct withAllDataFromProperty() {
        CloudProduct testProduct = new CloudProduct(TestDataReader.getTestData(COMPUTE_ENGINE_AREA),
                TestDataReader.getTestData(OPERATING_SYSTEM));
        testProduct.setNumberOfInstances(NUMBER_OF_INSTANCES);
        testProduct.setProvisioningModel(PROVISIONING_MODEL);
        testProduct.setSeries(SERIES);
        testProduct.setMachineType(MACHINE_TYPE);
        testProduct.setGpuType(GPU_TYPE);
        testProduct.setNumberOfGPU(NUMBER_OF_GPUS);
        testProduct.setLocalSSD(LOCAL_SSD);
        testProduct.setDataCenterLocation(DATACENTER_LOCATION);
        testProduct.setCommittedUsage(COMMITTED_USAGE);

        return testProduct;
    }
}
