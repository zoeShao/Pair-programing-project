# a2-starter

# Explain which features were pair programmed
- All the features are pair programmed: (below are the order of our completion)
	1. Feature #1: Submit a new order
	2. Feature #5: Ask for the menu using one of the following
	3. Feature #2: Update existing order
	4. Feature #3: Cancel order
	5. Feature #4: Ask for pickup or delivery
- Note:   
1.We have kept the old branch "Pair" because it indicates the original design pattern we have chosen to complete Feature #1.  
2.We seperated completing the whole task into implementing all the features first and then write tests for them. But all of them are pair programming.  
3.We didn't use only one computer during each process of completing one feature because on the handout it said the driver should use their own GitHub account, after we took a break and switched roles we just use another one's computer and do pair programming. (So the commit histroy may contain 2 gihub accounts, but we have clearly stated who is the driver and who is the navigator in the commit history.)

# Explain who the driver and navigator was for different parts of the features
- Implementing all the features:
	1. Feature #1 (Submit a new order):
		- Part1: Created Pizza and Drink Classes
			- Driver: linshiq4
			- Navigator: shaoyuti
		- Part2: Created Order Class and updated PizzaPalour
			- Driver: shaoyuti
			- Navigator: linshiq4
		- Part3: Deleted Drink Class & Added price and recipe files & Created ParlourFileReader Class & Updated PizzaParlour
			- Driver: linshiq4
			- Navigator: shaoyuti
		- Part4: Created OrderMaker & Updated PizzaParlour & Fixed some bugs wrote by the previous driver.
			- Driver: shaoyuti
			- Navigator: linshiq4
		- Part 5: Implemented part details of OrderMaker created by the previous driver in PizzaParlour. & Wrote more helper functions to Handler, Create AllData class. & **Completed the feature**.
			- Driver: linshiq4
			- Navigator: shaoyuti
	2. Feature #5 (Ask for the menu using one of the following):
		- Part 1: Created Menu Class and updated PizzaParlour, OrderMaker and Handler Classes to implement it.
			- Driver: shaoyuti
			- Navigator: linshiq4
		- Part 2: Fixed the exit instruction message.
			- Driver: linshiq4
			- Navigator: shaoyuti
		- Part 3: Updated OrderMaker class and Handler class & **Completed the feature**.
			- Driver: shaoyuti
			- Navigator: linshiq4
	3. Feature #2 (Update existing order) & Feature #3 (Cancel order): 
		- Part 1: Create OrderUpdater class & Finished update pizza option & Finished update drink & **Completed the feature**.
			- Driver: linshiq4
			- Navigator: shaoyuti
	4. Feature #4 (Ask for pickup or delivery):
		- Part 1: Fixed some bugs written by the previous driver & Created DeliveryMaker Class & **Completed the feature**.	
			- Driver: shaoyuti
			- Navigator: linshiq4
- Writing tests for all the features:
	1. Feature #1 (Submit a new order):
		- Part1: Created test files & Finished testing Pizza and Order & Finished testing FileReaders
			- Driver: linshiq4
			- Navigator: shaoyuti
	2. Feature #5 (Ask for the menu using one of the following):
		- Part1: Finished testing Menu
			- Driver: linshiq4
			- Navigator: shaoyuti
	
# Reflection

- What we liked:  
	1. Since we have different prior experiences and ways of understanding or interpreting the task at hand, we may have different ideas for solutions.  
	2. Since our different roles in the team can bring new perspectives, both of us are more keen to each other's problem. So every time someone is stuck, the other people will easily find where the problem is, which reduced the time for debugging. 
	3. With two minds, the quality of the code much more higher and reliable.

- What we disliked:
	1. Sometimes we cannot agree towards the same thing especially when we have a completely opposite idea and both of us think we are right, so it is hard to make a decision. 
	2. Sometimes it is a little bit waste of time when some feature is quite simple.

# Program design
- At first, we used CRC cards and UML to design the whole structure. 
- Initially, we planned to use Abstract Factory design pattern for the creation of Pizza instance. We decided to create a pizza interface and use Factory to construct Object. Also, we have planned to use Adapter design pattern on Feature #4 to generate the expected format.
- After we have checked the piazza and in order to satisfied the requirement of adding new types of pizza without need to call the developer to write new class for the pizza shop, we decided to use Builder design pattern and make the Pizza a concrete class. The reason why we gave up using the design pattern of Abstract Factory/Factory Method anymore is we did not know how to dynamically create class while the program is running. 
- We changed our mind and decided to write class method instead of using Adapter design pattern because we think it is used to connect two Objects not two different file formats.

# Tools used to help create clean code
IDE tools, google java formatter and google check style.
