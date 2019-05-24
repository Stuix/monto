package com.pvtgrupp8.monto;

import com.pvtgrupp8.monto.dao.*;
import com.pvtgrupp8.monto.entities.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RelationTests {

    @Autowired
    private TestEntityManager entityManager;

    private String [] testStrings = {"Hydrogen", "Helium", "Lithium", "Beryllium", "Boron",
        "Carbon", "Nitrogen","Hydrogen", "Helium", "Lithium", "Beryllium", "Boron", "Carbon", "Nitrogen",
        "Oxygen", "Fluorine", "Neon", "Sodium", "Magnesium", "Aluminum", "Silicon",
        "Phosphorus", "Sulfur", "Chlorine", "Argon", "Potassium", "Calcium",
        "Scandium", "Titanium", "Vanadium", "Chromium", "Manganese", "Iron",
        "Cobalt", "Nickel", "Copper", "Zinc", "Gallium", "Germanium", "Arsenic",
        "Selenium", "Bromine", "Krypton", "Rubidium", "Strontium", "Yttrium",
        "Zirconium", "Niobium", "Molybdenum", "Technetium", "Ruthenium", "Rhodium",
        "Palladium", "Silver", "Cadmium", "Indium", "Tin", "Antimony", "Tellurium",
        "Iodine", "Xenon", "Cesium", "Barium", "Lanthanum", "Cerium",
        "Praseodymium", "Neodymium", "Promethium", "Samarium", "Europium",
        "Gadolinium", "Terbium", "Dysprosium", "Holmium", "Erbium", "Thulium",
        "Ytterbium","Lutetium", "Hafnium", "Tantalum", "Tungsten", "Rhenium",
        "Osmium", "Iridium", "Platinum", "Gold","Mercury","Thallium", "Lead",
        "Bismuth", "Polonium", "Astatine", "Radon", "Francium", "Radium",
        "Actinium", "Thorium", "Protactinium", "Uranium"};


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private AttractionRepository attractionRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CreatorRepository creatorRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private FunFactRepository funFactRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Test
    public void testAddActiveRouteToUser() {
        Route route = new Route();
        route.setRouteName("testrutt");
        User user = new User();
        user.setEmail("jovars@gmail.com");
        user.setUsername("TestUser");
        user.setActiveRoute(route);
        routeRepository.save(route);
        userRepository.save(user);

        assertSame(user,userRepository.findByEmail("jovars@gmail.com"));
        assertSame(route,userRepository.findById(user.getId()).getActiveRoute());
    }

    @Test
    public void testCreateRouteAndAddToUser(){
        List<User> users = new ArrayList<>();
        List<Route> routes = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            User tempUser = new User();
            Route tempRoute = new Route();
            tempUser.setUsername("Test"+i);
            tempUser.setEmail("test@test.com"+i);
            tempRoute.setRouteName("Route "+i);
            tempRoute.setDescription("Den best route "+i);
            users.add(tempUser);
            routes.add(tempRoute);
            userRepository.save(tempUser);
            routeRepository.save(tempRoute);
        }

        for (int i = 0; i < 20 ; i++) {
            int randUser = (int) (Math.random() * 20 +1);
            int randRoute = (int) (Math.random() * 20 +1);

            User tempUser = userRepository.findById(randUser);
            tempUser.addCreatedRoute(routeRepository.findById(randRoute));
            tempUser.addCreatedRoute(routeRepository.findById(randUser));
            userRepository.save(tempUser);
            List<Route> userRoutes = userRepository.findById(randUser).getRoutes();

            assertTrue(userRoutes.contains(routeRepository.findById(randRoute)) &&
                userRoutes.contains(routeRepository.findById(randUser)));
            assertSame(routeRepository.findById(randRoute).getRouteCreator(),userRepository.findById(randUser));


        }


    }

    @Test
    public void testAddRatingToRouteAndUser() {
        User testUser = createUser();
        Rating testRating = createRating();
        Route testRoute = createRoute();
        testUser.setActiveRoute(testRoute);
        testRoute.addRating(testRating);

        userRepository.save(testUser);
        ratingRepository.save(testRating);

        User repUser = userRepository.findById(testUser.getId());

        assertSame(repUser, testUser);
        assertTrue((repUser.getActiveRoute().getRatings().contains(testRating)));
        assertSame(ratingRepository.getOne(testRating.getId()),testRating);
        assertSame(repUser.getActiveRoute(),testRating.getRoute());

    }

    @Test
    public void testAddAttractionsToRoute() {
        Attraction testAttraction;
        Route testRoute = createRoute();
        for (int i = 0; i < 20 ; i++) {
          testAttraction = createAttraction();
          attractionRepository.save(testAttraction);
          testRoute.addAttraction(testAttraction);
        }
        routeRepository.save(testRoute);
        Route repRoute = routeRepository.findById(testRoute.getId());
        assertEquals(repRoute.getRouteName(),testRoute.getRouteName());
        List<Attraction> repAttractions = attractionRepository.findAll();
        assertEquals(repRoute.getAttractions().size(),(repAttractions).size());
    }

    @Test
    public void testCitytoDistricttoPositiontoAttractiontoCreator(){
        City city = new City(testStrings[(int)(Math.random()*testStrings.length)]);
        District district = new District(testStrings[(int)(Math.random()*testStrings.length)]);
        Position position = new Position(59.314254,18.0616341);
        Attraction attraction = createAttraction();
        Creator creator = createCreator();

        city.add(district);
        district.add(position);
        attraction.setPosition(position);
        creator.add(attraction);

        cityRepository.save(city);
        districtRepository.save(district);
        positionRepository.save(position);
        attractionRepository.save(attraction);
        creatorRepository.save(creator);

        City repCity = cityRepository.getOne(city.getId());
        District repDistrict = districtRepository.getOne(district.getId());
        Position repPosition = positionRepository.getOne(district.getId());
        Attraction repAttraction = attractionRepository.getOne(attraction.getId());
        Creator repCreator = creatorRepository.getOne(creator.getId());

        assertEquals(repAttraction.getPosition().getDistrict().getName(),repDistrict.getName());
        assertEquals(repAttraction.getCreators().get(0).getFirstName(),repCreator.getFirstName());
        assertEquals(creator.getFirstName()+creator.getLastName(),
            repCreator.getFirstName()+repCreator.getLastName());

    }


    private User createUser() {
        int randUsername = (int) (Math.random() * testStrings.length + 1);
        int randEmail = (int) (Math.random() * testStrings.length + 1);
        User testUser = new User();
        testUser.setUsername(testStrings[randUsername]);
        testUser.setEmail(testStrings[randEmail] + "@hotmail.com");
        return testUser;
    }

    private Rating createRating() {
        Rating testRating = new Rating();
        testRating.setComment("Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
            " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        testRating.setRating(Math.random()*5);
        return testRating;
    }

    private Route createRoute() {
        int randRouteName = (int) (Math.random() * testStrings.length);
       Route testRoute = new Route();
        testRoute.setRouteName("The "+testStrings[randRouteName]+" Route!");
        testRoute.setDescription("Ut enim ad minim veniam, quis nostrud exercitation" +
            "ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        testRoute.setRouteIsPublic(randRouteName % 2 == 0);
        return testRoute;
    }

    private Attraction createAttraction() {
        int randAttractionName = (int) (Math.random() * testStrings.length);
        Attraction testAttraction = new Attraction();
        testAttraction.setTitle(testStrings[randAttractionName]);
        return testAttraction;
    }

    private Creator createCreator() {
        Creator creator = new Creator();
        creator.setFirstName(testStrings[(int)(Math.random()*testStrings.length)]);
        creator.setLastName(testStrings[(int)(Math.random()*testStrings.length)]);
        creator.setDescription("Ut enim ad minim veniam, quis nostrud exercitation\" +\n" +
            "            \"ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        return creator;
    }


    
}
