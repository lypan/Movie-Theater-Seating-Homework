# Home Seating HomeWork
#### What is it?
I simulate a service that allocate movie theater seats for users to achieve max satisfaction and seats usage.
I use the modified bin packing algorithm and make three assumption.
1. Each request must be fit into the theater, if there are no seats left for the group in whole theater, the request won't be handle.
2. Each group must be close together, so seats must be in rectangle or square.
3. The seats in the middle range will let customers more satisfactory, so allocate seats from middle first.

#### Special features
1. A complete simulation of backend system with response and request communication.
2. Great system design that obey OOP design and very easily to extend function without modifying original code.
3. Theater now can be any size and the group won't be divided and will have the best seats.

#### Algorithm analysis
1. First Fit Decreasing uses at most `(4M + 1)/3` bins if the optimal is `M`.
2. Time complexity: since I sort the request by seat number first and then traverse all seats in the theater. If `N` requests and `M` seats, total time is `O(NlogN + M)`

#### How to run the console
1. Go into the root folder of the repository.
2. Then run the following command.
##### For service
Input and output file will in the 'files' directory.
If omitted inputFilePath or outputFilePath, will use the default filePath(in the files directory).
##### Default
```
gradle clean build

java -cp build/libs/Walmart-1.0-SNAPSHOT.jar Main
```
##### If you want to add input file and output file
```
gradle clean build

java -cp build/libs/Walmart-1.0-SNAPSHOT.jar Main --input inputFilePath --output outputFilePath
```
##### For test
```
gradle clean test
```
#### Design Pattern
Dependency inversion: use interface to delegate work, and high-level class not depends on low-level class directly. So, it is very easy to extend functions in the future.

Singleton: Use singleton fore better resource management and ensure that only one main service is running for the entire time.
