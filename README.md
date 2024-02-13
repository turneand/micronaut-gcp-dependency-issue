Welcome to this simple Micronaut App
==============================================

This sample application is used to highlight an issue we observed with Micronaut's GCP
dependencies when they conflict with each other.

This particular project demonstrates gax library compatibility issues when both logging and secret-manager are used
 - micronaut-gcp-logging:5.4.0 -> micronaut-gcp-common:5.4.0 -> google-cloud-core:2.31.0 -> gax:2.41.0
 - micronaut-gcp-secret-manager:5.4.0 -> google-cloud-secretmanager:2.33.0 -> gax:2.39.0 & gax-grpc:2.39.0 & gax-httpjson:2.39.0

The issue here is if gax:2.41.0 is "chosen", then it's incompatible with gax-grpc:2.39.0 and gax-httpjson:2.39.0,
resulting in the following errors.

```
[ERROR] TestApp.simpleTest -- Time elapsed: 1.609 s <<< ERROR!
java.lang.AbstractMethodError: Receiver class com.google.api.gax.grpc.GrpcCallContext does not define or inherit an implementation of the resolved method 'abstract com.google.api.gax.rpc.ApiCallContext withEndpointContext(com.google.api.gax.rpc.EndpointContext)' of interface com.google.api.gax.rpc.ApiCallContext.
        at com.google.api.gax.rpc.ClientContext.create(ClientContext.java:233)
        at com.google.cloud.secretmanager.v1.stub.GrpcSecretManagerServiceStub.create(GrpcSecretManagerServiceStub.java:248)
        at com.google.cloud.secretmanager.v1.stub.SecretManagerServiceStubSettings.createStub(SecretManagerServiceStubSettings.java:349)
        at com.google.cloud.secretmanager.v1.SecretManagerServiceClient.<init>(SecretManagerServiceClient.java:452)
        at com.google.cloud.secretmanager.v1.SecretManagerServiceClient.create(SecretManagerServiceClient.java:434)
        at TestApp.simpleTest(TestApp.java:17)
        at java.base/java.lang.reflect.Method.invoke(Method.java:568)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
```

This was raised as https://github.com/micronaut-projects/micronaut-gcp/issues/1046