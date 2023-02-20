# Garage
</br>
</br>
Create the form for ordering automobile spare parts.</br>
You can use TextView where it's needed.</br>
Fields for input or select information:</br>
* Client's name (EditView)</br>
* Phone number (EditView)</br>
* Spinner for auto brand select (add at least 3).</br>
* Spinner for auto model select (add at least 3 per each brand).</br>
* Spare parts list - ListView (the same for every car).</br>
* Order button - writes filled and selected data into log.</br>
* In spare parts list you have to add description and image of spare part according model below.</br>
Model:</br>
Leftward (10% from model width) - square picture of spare part.</br>
On the right - 2-3 rows, that located below each other.</br>
At the 1st row is a name of spare part.</br>
At the 2nd row (and if needed in 3rd also) is a description of spare part.</br>
Between the image (on the left) and the text (on the right) is a margin.</br>
</br>
</br>
How to work app:</br>
1. Field "Full Name" accepts only first name with last name. So it must be 2 words with space between. At least with 2 symbols length each.</br>
- Type there Roman, and field didn't catch it.</br>
After clicking "Place an order", you will receive toast that field has to be filled.</br>
+ Hint of field show you example John Snow.</br>
Type it, click "Place an order" and now you receive toast with issue about phone number.</br>
2. Field "Phone number" accepts only Ukraine phone numbers that starts from +380 and contain 12 digits in total.</br>
- Type there 380990000111, and field didn't accept it.</br>
After clicking "Place an order", you will receive toast that field has to be filled.</br>
+ Hint of field show you example +380970123456.</br>
Type it, click "Place an order" and now you receive toast with issue about car model.</br>
3. Field "Car brand" that shows Nothing selected below label Car brand.</br>
- If you don't select here any brand and click "Place an order", you will receive toast with issue about car model.</br>
But if you select any brand of car you will be able to choose the model of car later.</br>
+ Pick up from the list of brands - Opel.</br>
4. Field "Car model" that shows Nothing selected below label Car model.</br>
- If you don't select here any model and click "Place an order", you will receive toast with issue about car model.</br>
+ Pick up from the list of models - Ascona, click "Place an order" and now you receive toast with issue about spare parts.</br>
5. List "Spare parts" that shows spare parts with pictures and description below label List of spare parts.</br>
When you click over spare part, the background of these spare part changes color to darkest.</br>
- If you don't select here any spare part and click "Place an order", you will receive toast that at least one spare part had to be choosen.</br>
+ Click over Engine, Transmission. After that click on the button "Place an order".</br>
Logcat with tag "Main Activity", type danger or d will show you the next information:</br>
Customer's name: John Snow</br>
Customer's phone: +380 97 012 3456</br>
Car brand: Opel</br>
Car model: Ascona</br>
List of ordered spare parts:</br>
Engine</br>
Transmission</br>
</br>
