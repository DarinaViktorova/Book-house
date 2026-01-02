import { useEffect, useState } from "react";
import productsData from "./data/products.json"; // Import the mock data from the JSON file

export const useGetAllProducts = () => {
	const [products, setProducts] = useState([]);
	const [isLoading, setIsLoading] = useState(false);

	useEffect(() => {
		setIsLoading(true);
		// Simulate an asynchronous delay to mimic a network request
		const fetchData = async () => {
			try {
				// Simulate a delay of 1 second before setting the products
				setTimeout(() => {
					setProducts(productsData);
					setIsLoading(false);
				}, 1000);
			} catch (error) {
				console.error("Error fetching data:", error);
				setIsLoading(false);
			}
		};
		fetchData();
	}, []);

	return { products, isLoading };
};
