import axios from "axios";

export const logout = async () => {
	// Simulate logout process
	return new Promise((resolve, reject) => {
		setTimeout(() => {
			resolve({ status: 200 }); // Simulate successful logout
			// For error simulation:
			// reject({ message: "Logout failed", code: "LOGOUT_ERROR" });
		}, 1000); // Simulate 1 second delay
	});
};
