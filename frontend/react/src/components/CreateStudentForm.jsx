import { Formik, Form, useField } from 'formik';
import * as Yup from 'yup';
import {Alert, FormLabel, Input, AlertIcon, Select, Box, Button, Stack} from "@chakra-ui/react";
import {addStudent} from "../services/client.js";
import {errorNotification, successNotification} from "../services/Notification.js";

const MyTextInput = ({ label, ...props }) => {
    const [field, meta] = useField(props);
    return (
        <Box>
            <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
            <Input className="text-input" {...field} {...props} />
            {meta.touched && meta.error ? (
                <Alert className="error" status={"error"} mt={2}>
                    <AlertIcon/>
                    {meta.error}</Alert>
            ) : null}
        </Box>
    );
};

const MySelect = ({ label, ...props }) => {
    const [field, meta] = useField(props);
    return (
        <Box>
            <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
            <Select {...field} {...props} />
            {meta.touched && meta.error ? (
                <Alert className="error" status={"error"} mt={2}>
                    <AlertIcon/>
                    {meta.error}</Alert>
            ) : null}
        </Box>
    );
};

const CreateStudentForm = ({fetchStudents}) => {
    const phoneRegExp = "/^((\\\\+[1-9]{1,4}[ \\\\-]*)|(\\\\([0-9]{2,3}\\\\)[ \\\\-]*)|([0-9]{2,4})[ \\\\-]*)*?[0-9]{3,4}?[ \\\\-]*[0-9]{3,4}?$/"
    const emailRegExp = "/[a-zA-Z][a-zA-Z0-9]+@(?:[a-zA-Z0-9]+\\.)+[A-Za-z]+$/"
    return (
        <>
            <Formik
                initialValues={{
                    firstName: "",
                    lastName: "",
                    phoneNumber: "",
                    password: "",
                    email: "",
                    age: 0,
                    gender: "",
                }}
                validationSchema={Yup.object({
                    firstName: Yup.string()
                        .max(15, 'Must be 15 characters or less')
                        .required('Required'),
                    lastName: Yup.string()
                        .max(15, 'Must be 15 characters or less')
                        .required('Required'),
                    phoneNumber: Yup.string()
                        .max(14, 'Must be 14 characters or less')
                        .matches({phoneRegExp},"Phone number is not valid")
                        .required('Required'),
                    email: Yup.string()
                        .email('Must be 20 characters or less')
                        .matches({emailRegExp}, "Please enter a valid email")
                        .required('Required'),
                    password: Yup.string()
                        .min(4, 'Must be 4 characters or more')
                        .required('Required'),
                    age: Yup.number()
                        .min(18, 'Must be at least 18 years of age')
                        .max(100, "Must be less than 100 years old ")
                        .required('Required'),
                    gender: Yup.string()
                        .oneOf(
                            ['MALE', 'FEMALE'],
                            'Invalid gender '
                        )
                        .required('Required'),
                })}
                onSubmit={(student, { setSubmitting }) => {
                    setSubmitting(true)
                    addStudent(student).then(res => {
                        successNotification("Student Saved",`${student.firstName} ${student.lastName} was successfully saved`)
                        fetchStudents()
                    }).catch(err => {
                        errorNotification(err.code, err.response.data.message)
                    }).finally(() => {
                        setSubmitting(false)
                    })
                }}
            >
                {({isValid, isSubmitting, dirty}) => (
                    <Form>
                        <Stack spacing={"24px"}>
                            <MyTextInput
                                label="First Name"
                                name="firstName"
                                type="text"
                                placeholder="Jane"
                            />

                            <MyTextInput
                                label="Last Name"
                                name="lastName"
                                type="text"
                                placeholder="Doe"
                            />

                            <MyTextInput
                                label="Email Address"
                                name="email"
                                type="email"
                                placeholder="jane@email.com"
                            />

                            <MyTextInput
                                label="Password"
                                name="password"
                                placeholder={"Enter a password"}
                            />

                            <MyTextInput
                                label="Phone Number"
                                name="phoneNumber"
                                type="text"
                                placeholder="Contact Number"
                            />

                            <MyTextInput
                                label="Age"
                                name="age"
                                type="number"
                                placeholder="age"
                            />

                            <MySelect label="Gender" name="gender">
                                <option value="">Select Gender</option>
                                <option value="MALE">Male</option>
                                <option value="FEMALE">Female</option>
                            </MySelect>

                            <Button isDisabled={!isValid||isSubmitting}  type="submit">Submit</Button>
                        </Stack>
                    </Form>
                )}
            </Formik>
        </>
    );
};

export default CreateStudentForm