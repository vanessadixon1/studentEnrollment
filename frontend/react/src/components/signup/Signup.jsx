import {useEffect} from "react";
import {useAuth} from "../context/AuthContext.jsx";
import CreateStudentForm from "../shared/CreateStudentForm.jsx";
import {useNavigate} from "react-router-dom";
import {Flex, Heading, Image, Link, Stack, Text} from "@chakra-ui/react";
import enrollment from '../../../public/maccomputer.jpg'

const Signup = () => {
    const { student, setStudentFromToken } = useAuth();
    const navigate = useNavigate();

    useEffect(() => {
        if (student) {
            navigate("/dashboard");
        }
    })

    return (
        <Stack minH={'100vh'} direction={{base: 'column', md: 'row'}}>
            <Flex p={8} flex={1} alignItems={'center'} justifyContent={'center'}>
                <Stack spacing={4} w={'full'} maxW={'md'}>
                    <Heading fontSize={'2xl'} mb={15}>Register for an account</Heading>
                    <CreateStudentForm onSuccess={(token) => {
                        localStorage.setItem("access_token", token)
                        setStudentFromToken()
                        navigate("/dashboard")
                    }}/>
                    <Link color={"blue.500"} href={"/"}>
                        Have an account? Login now.
                    </Link>
                </Stack>
            </Flex>
            <Flex
                flex={1}
                p={10}
                flexDirection={"column"}
                alignItems={"center"}
                justifyContent={"center"}
                bgGradient={{sm: 'linear(to-r, blue.600, purple.600)'}}
            >
                <Text fontSize={"6xl"} color={'white'} fontWeight={"bold"} mb={5}>
                    <Link target={"_blank"} href={"/"}>
                        Enroll Now
                    </Link>
                </Text>
                <Image
                    alt={'Login Image'}
                    objectFit={'scale-down'}
                    src={enrollment}
                />
            </Flex>
        </Stack>
    );
}

export default Signup