import axios from "axios";

export const register = async (userData) => {
	try {
		// Get existing data from localStorage or initialize an empty array if it doesn't exist
		const existingData = localStorage.getItem("userData");
		const newData = existingData ? JSON.parse(existingData) : [];

		// Add new user data to the array
		newData.push(userData);

		// Save the updated data back to localStorage
		localStorage.setItem("userData", JSON.stringify(newData));

		// Return success response
		return { success: true, message: "User registered successfully" };
	} catch (error) {
		// Handle any errors
		throw error;
	}
};
