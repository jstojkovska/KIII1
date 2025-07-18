import useCountries from "../../../hooks/useCountries.js";
import {useState} from "react";
import {Box, Button, CircularProgress} from "@mui/material";
import CountriesGrid from "../../components/countries/CountriesGrid/CountriesGrid.jsx";
import AddCountryDialog from "../../components/countries/AddCountryDialog/AddCountryDialog.jsx";

const CountriesPage = () => {

    const {countries, loading, onEdit, onDelete, onAdd} = useCountries()
    const [addCountryDialog, setAddCountryDialog] = useState(false)

    return (
        <>
            <Box className="products-box">
                {loading && (
                    <Box className="progress-box">
                        <CircularProgress/>
                    </Box>
                )}
                {!loading &&
                    <>
                        <Box sx={{display: "flex", justifyContent: "flex-end", mb: 2}}>
                            <Button variant="contained" color="primary" onClick={() => setAddCountryDialog(true)}>
                                Add Country
                            </Button>
                        </Box>
                        <CountriesGrid countries={countries} onEdit={onEdit} onDelete={onDelete}/>
                    </>}
            </Box>
            <AddCountryDialog
                open={addCountryDialog}
                onClose={() => setAddCountryDialog(false)}
                onAdd={onAdd}
            />
        </>
    )

}
export default CountriesPage;