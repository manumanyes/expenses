var app = angular.module('expenseApp', []);
app.controller('expensesController', function($scope, $http) {

	$http.get("http://localhost:8080/expense/").then(
		function (response) {
			console.log("Response is", response);
			$scope.reset();
			$scope.expenseList = response.data;
			console.log("expenseList", $scope.expenseList);
			console.log("expenseList is array", Array.isArray($scope.expenseList));
			if(!Array.isArray($scope.expenseList)) {
				$scope.expenseList = [];
			}
		}, 
		function (response){
			console.log("No data", response);
			$scope.expenseList = [];
			$scope.reset();
		}
	);
	 
	$scope.delete = function(expense) {
		console.log("Deleting: ", expense);
		$http.delete("http://localhost:8080/delete/"+expense.id).then(
           function (response){
				console.log("Deleted OK. ID:", expense.id, ",Description:", expense.description);
				var index = $scope.expenseList.indexOf(expense);
				$scope.expenseList.splice(index, 1);
				$scope.reset();
           }, 
		   
           function (response){
				console.log(response);
				alert("Error! Expense has not been deleted");
           }
        );
    }
	
	$scope.edit = function(expense) {
		$scope.editIndex = $scope.expenseList.indexOf(expense);
		console.log("Edit: ", expense, ", index:", $scope.editIndex);
		
		if(expense.date != null) {
			var jsDate = new Date(expense.date);
			expense.date = jsDate;
		}
		console.log("Edit with date conversion:", expense);
		
		$scope.expense = angular.copy(expense);
    	$scope.action = "Edit";
    }
	
	
   $scope.validate = function(form, expense) {
        console.log("validate", expense);
		console.log("form valid", form);
		if(!form) {
			alert("Missing or incorrect data");
			return;
		}
		if(expense.id == null) {
			$http.post("http://localhost:8080/create/", expense)
			.then(
				function (response){
					console.log("Created OK", response);
					
					$scope.expenseList.push(response.data);
					$scope.reset();
				}, 
		   
				function (response){
					console.log(response);
					alert("Error! Expense has not been created");
				}
			);
		} else {
			$http.put("http://localhost:8080/update/"+expense.id, expense)
			.then(
				function (response){
					console.log("Update OK", response);
					
					var updatedExpense = response.data;
					//console.log("MyOriginalExpense", $scope.expenseList[$scope.editIndex]);
					angular.copy(updatedExpense, $scope.expenseList[$scope.editIndex]);
					
					$scope.reset();
				}, 
		   
				function (response){
					console.log(response);
					alert("Error! Expense has not been updated");
				}
			);
		}
		
    };
	
	$scope.reset = function() {
		$scope.expense = null;
		$scope.editIndex = null;
		$scope.action = "Create";
    }
  
});