# Home Seating HomeWork
#### What is it?
I simulate a service that allocate movie theater seats for users to achieve max satisfaction and seats usage.
I use the modified bin packing algorithm and make three assumption.
1. Each request must be fit into the theater, if there are no seats left for the group in whole theater, the request won't be handle.
2. Each group must be close together, so seats must be in rectangle or square.
3. The seats in the middle range will let customers more satisfactory, so allocate seats from middle first.

#### How to run the console
1. Go into the root folder of the repository.
2. Then run the following command.
For service
Input and output file will in the 'files' directory.
If omitted inputFilePath or outputFilePath, will use the default filePath(in the files directory).
```
gradle clean build

java -cp build/libs/Walmart-1.0-SNAPSHOT.jar Main --input inputFilePath --output outputFilePath
```
For test
```
gradle clean test
```
#### Design Pattern
Dependency inversion: use interface to delegate work, and high-level class not depends on low-level class directly. So, it is very easy to extend functions in the future.

Singleton: Use singleton fore better resource management and ensure that only one main service is running for the entire time.
